<template>
        <div class="text-center">
            <p>
              Copyright (C) WYDY.SHOP {{year}}, All Rights Reserved  沪ICP备17054777
                <a href="http://wydy.shop/">WYDY</a>
            </p>
        </div>
</template>

<script>
  import  theme from '../themes/AppTheme.js'
    export default {
      computed:{
        year:function(){
          return new Date().getFullYear();
        }
      },
      data() {
        return {
          theme: theme,
        }
      }
    };
</script>
