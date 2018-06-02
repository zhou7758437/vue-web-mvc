<template>
  <el-menu
    :default-active="activeIndex2"
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
    :backgroundColor="theme.backgroundColor"
    :textColor="theme.textColor"
    :activeTextColor="theme.activeTextColor">
    <el-menu-item index="1">处理中心</el-menu-item>
    <el-submenu index="2">
      <template slot="title">我的工作台</template>
      <el-menu-item index="2-1">选项1</el-menu-item>
      <el-menu-item index="2-2">选项2</el-menu-item>
      <el-menu-item index="2-3">选项3</el-menu-item>
      <el-submenu index="2-4">
        <template slot="title">选项4</template>
        <el-menu-item index="2-4-1">选项1</el-menu-item>
        <el-menu-item index="2-4-2">选项2</el-menu-item>
        <el-menu-item index="2-4-3">选项3</el-menu-item>
      </el-submenu>
    </el-submenu>
    <el-menu-item index="3" disabled>消息中心</el-menu-item>
    <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>

    <el-submenu index="admin-1" v-if="$hasRole('admin')">
      <template slot="title">系统管理</template>
      <el-menu-item index="admin-1-1" @click="logout">用户管理</el-menu-item>
    </el-submenu>
    <el-submenu index="9999999999" class="menu-user-info">
      <template slot="title">{{user.nickName}}</template>
      <el-menu-item index="9999999999-1" @click="logout">注销</el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<style>
  .menu-user-info {
    float: right !important;
  }
</style>
<script>
  import bus from '../events/EventBus.js'
  import theme from '../themes/AppTheme.js'
  import store from '../store'

  export default {
    computed: {},
    methods: {
      init: function (event) {
        var self = this;
        this.$http.get('spring/user/curUser')
          .then(function (resp) {
            var user = resp.data;
            if (typeof user == 'string') {
              user = {};
            }else{
              self.user = user;
              store.commit('updateUser',user,{module:'user'});
            }



          }, function (err) {
            console.log(err);
          });
      },
      logout: function () {
        var self = this;
        this.$http.get('/spring/user/logout')
          .then(function (resp) {
            self.$toaster.info('注销成功');
            window.location.href = "open.html";
            self.init();
          }, function (err) {
            self.$toaster.error('系统异常')
            self.account.processing = false;
          });
      },
      goto: function (e) {
        var target = e.target.attributes.router.value;
        this.$router.push(target);
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    },
    mounted() {
      this.init();
      var self = this;
      bus.$on("userLoaded", function (data) {
        console.log(data) // 并不能收到
        self.init();
      })
    },
    data() {
      return {
        theme: theme,
        user: {},
        activeIndex: '1',
        activeIndex2: '1'
      }
    }
  };
</script>
