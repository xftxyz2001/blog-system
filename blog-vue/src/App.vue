<template >
  <!--  <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
  <router-view></router-view>

</template>

<script>

import Handler from "@/components/Handler.vue";

export default {
  name: 'App',
  components: {
    Handler
  },
}
</script>

<style scoped>
/*    #login {
        !*background: url("@/assets/4455.jpg") no-repeat center;*!
        top: 0;
        left: 0;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
    }*/
  #handler{
  position: relative;
}
.el-header{
  background: #50396d;
}
</style>
<style>
/*body{
    background-image:url("img/logIn.jpg");
    width:100%;
    height:100%;
    background-size:cover;
}*/
html,body,#app,.el-container{
  padding: 0px;
  margin: 0px;
  height: 100%;
  border: 0;
}
</style>
<style lang="scss">
body {
  font-family:”Microsoft YaHei”,Arial,Helvetica,sans-serif,”宋体”;

}
</style>
