<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" replace>
      <el-breadcrumb-item :to="{ name: 'home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row class="row-split">
      <el-form inline class="search-form">
        <el-form-item>
          <el-input v-model="options.query" placeholder="请输入关键字"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="searchUser" type="primary" :loading="options.loading" icon="el-icon-search">搜索</el-button>
        </el-form-item>
      </el-form>
    </el-row>
  </div>
</template>
<style>
  .search-form{
    float: right;
  }
</style>
<script>
  export default {
    name: "UserManage",
    methods:{
      searchUser(){
        var param=this.options;
        if(!param.query){
          return;
        }
        console.log(this.options);
      }
    },
    data(){
      return{
        options:{}
      }
    }
  }
</script>

<style scoped>

</style>
