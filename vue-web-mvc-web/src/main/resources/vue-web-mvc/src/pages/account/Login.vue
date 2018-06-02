<template>
  <el-card class="box-card">
    <h1>登录页面</h1>
    <el-form class="login-form" label-width="100px" v-loading="account.processing">
      <el-form-item label="账号" prop="pass">
        <el-input v-model="account.userName"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="checkPass">
        <el-input type="password" v-model="account.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login">登录</el-button>
        <el-button @click="goRegister">注册</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>
<style>
  .login-form {
    width: 500px;
  }
</style>
<script>
  import Vue from 'vue'

  export default {
    computed: {},
    mounted: function () {
      var self = this;
      this.$http.get('spring/user/curUser')
        .then(function (resp) {
          var user = resp.data;
          if (typeof user == 'string') {
            user = {};
          }
          self.user = user;


        }, function (err) {
          console.log(err);
        });
    },
    methods: {
      login: function (event) {
        if (!this.account.userName) {
          this.$toaster.warning('请输入用户名')
          return;
        }
        if (!this.account.password) {
          this.$toaster.warning('请输入密码')
          return;
        }
        if (this.account.processing) {
          this.$toaster.warning('正在登录')
          return;
        }
        this.account.processing = true;
        var self = this;
        this.$http.post('api/web/logIn', this.account)
          .then(function (resp) {
            var data = resp.data;
            if (data.success) {
              self.$toaster.info('登录成功');
              window.location.href = "index.html";

            } else {
              self.$toaster.error(data.message)
            }
            self.account.processing = false;
          }, function (err) {
            self.$toaster.error('系统异常')
            self.account.processing = false;
          });
      },
      init: function (event) {
        var self = this;
        this.$http.get('spring/user/curUser')
          .then(function (resp) {
            var user = resp.data;
            if (typeof user == 'string') {
              user = {};
            }
            self.user = user;


          }, function (err) {
            console.log(err);
          });
      },
      goRegister: function () {
        this.$router.push({name:"register"});

      }
    },
    data() {
      return {
        account: {
          userName: '',
          password: '',
          processing: false
        },
        user: {}

      }
    }
  };
</script>
