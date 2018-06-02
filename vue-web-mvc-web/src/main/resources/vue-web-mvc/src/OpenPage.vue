<template>
  <el-container>
    <el-container>
      <!--<el-header class="app-header">-->
        <!--<m-nav></m-nav>-->
      <!--</el-header>-->
      <el-main class="open-container">
        <router-view></router-view>
      </el-main>
    </el-container>
    <el-footer :style="{backgroundColor:theme.backgroundColor,color:theme.textColor}" class="app-footer">
      <m-footer></m-footer>
    </el-footer>
  </el-container>
</template>
<style lang="scss">
  html, body, #app, .el-container {
    height: 100%;
    margin: 0;
    padding: 0;
  }

  .el-container {
    .app-header {
      padding: 0;
    }
  }

  .el-footer {
    & > div {
      text-align: center;
    }

  }

  .app-aside {
    .el-menu {
      height: 100%;
      border-right: none;
    }
  }

  .row-split {
    margin-top: 10px;
  }
  .open-container{
    display: flex;
    align-items: center;
    justify-content: center;
  }
</style>
<script>
  import mNav from './components/Navbar.vue';
  import mFooter from './components/Footer.vue';
  import mAside from './components/Aside.vue';
  import theme from './themes/AppTheme.js'

  export default {
    name: 'OpenPage',
    components: {mNav, mFooter, mAside},
    data() {
      return {
        theme: theme,
      }
    }
  }
</script>

