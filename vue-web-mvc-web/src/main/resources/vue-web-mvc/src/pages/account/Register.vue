<template>
  <el-card class="box-card">
    <h1>注册页面</h1>
    <el-form class="login-form" label-width="100px" v-loading="account.processing">
      <el-form-item label="账号" prop="pass">
        <el-input v-model="account.userName"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="checkPass">
        <el-input type="password" v-model="account.password"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input type="password" v-model="account.passwordConfirm"></el-input>
      </el-form-item>
      <el-form-item label="昵称" prop="pass">
        <el-input v-model="account.nickName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="register">注册</el-button>
        <el-button @click="goLogin">登录</el-button>
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

  export default {
    computed: {},
    mounted: function () {
      var self = this;
    },
    methods: {
      register: function (event) {
        console.log(event);
        console.log(this.account);
        if (!this.account.userName) {
          this.$toaster.warning('请输入用户名')
          return;
        }
        if (!this.account.password) {
          this.$toaster.warning('请输入密码')
          return;
        }
        if (!this.account.passwordConfirm) {
          this.$toaster.warning('请再次输入密码')
          return;
        }
        if (this.account.passwordConfirm != this.account.password) {
          this.$toaster.warning('两次密码不一样')
          return;
        }
        if (!this.account.nickName) {
          this.$toaster.warning('请输入昵称')
          return;
        }
        if (this.account.processing) {
          this.$toaster.warning('正在注册')
          return;
        }
        this.account.processing = true;
        var self = this;
        this.$http.post('api/web/register', this.account)
          .then(function (resp) {
            var data = resp.data;
            if (data.success) {
              self.$toaster.info('注册成功');
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
      goLogin: function () {
        this.$router.push({name: "login"});

      }
    },
    data() {
      return {
        account: {
          userName: '',
          password: '',
          passwordConfirm: '',
          nickName: '',
          processing: false
        }

      }
    }
  };
</script>
