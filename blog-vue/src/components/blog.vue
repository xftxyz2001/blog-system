<template>
  <el-container >
    <el-aside width="200px">博主信息</el-aside>
    <el-container class ="sum">
      <el-header>
        <p>{{$route.params.name}}</p>
        <h2>{{ blogMD.title }}</h2>
      </el-header>

      <el-main>
        <div class="content markdown-body" id="content" v-html="blogMD.content"></div>

        <div>
          <div class="circle flex-h" @click="handleClick" :class="isUp?'check':''">
            <div class="img-box" :class="isUp?'img-box-check':''">
              <img v-if="isUp" src="../assets/support2.png" alt="" />
              <img v-else src="../assets/support1.png" alt="" />
            </div>
          </div>
          <div class="support-num">{{this.support}}</div>
          收藏 评论</div>
      </el-main>
    </el-container>
  </el-container>

</template>
<script>
export default{
  data(){
    return{
      isUp: false,
      support:0,
      blogMD:{
        id:'',//博客id
        userId:'',//用户id
        title:'',//标题
        description: "test",//简介
        content: "rest",//内容
        status: 0,//发布状态
      },
    }
  },
  methods: {
    handleClick () {
      if(this.isUp==false)
        this.support=this.support+1
      else
        this.support=this.support-1
      this.isUp = !this.isUp
    },
    onCollection(){
      if(this.isCollection){
        //取消收藏
        let params = {};
        let f_special_document_id = localStorage.getItem("f_special_document_id");
        params.f_special_document={
          f_special_document_id:f_special_document_id,
        };
        delSort(params).then(response => {
          if (response.data.httpStatus === 200) {
            this.$notify({
              title: "成功",
              message: "取消收藏",
              type: "success",
              offset: 100
            });
            this.key = 0; //使用key值区分两种状态的样式
            this.isCollection = false; //待收藏状态
            this.blogData.f_collection_sum -= 1; //收藏数减一
          } else {
            this.$notify.error({
              title: "错误",
              message: response.data.message,
              offset: 100
            });
          }
        });
      }else{
        //新增收藏
        let params = {};
        let f_special_document_id = localStorage.getItem("f_special_document_id");
        let f_special_document_name = localStorage.getItem("f_special_document_name");
        params.f_special_document={
          f_special_document_id:f_special_document_id,
          f_special_document_name:f_special_document_name,
        }
        addSort(params).then(response => {
          if (response.data.httpStatus === 200) {
            this.$notify({
              title: "成功",
              message: "收藏成功",
              type: "success",
              offset: 100
            });
            this.key = 1;
            this.isCollection = true;
            this.blogData.f_collection_sum += 1;
          } else {
            this.$notify.error({
              title: "错误",
              message: response.data.message,
              offset: 100
            });
          }
        })
      }
    },

  }
}
</script>
<style>
.sum{
  overflow-y: scroll;
  height: calc(100vh - 90px);
}
.el-container{
  padding: 0;
  /*外部间距也是如此设置*/
  margin: 0;
  /*统一设置高度为100%*/
  height: 100%;
  width: 100%;

}

.el-aside
{
  background-color: rgba(255,255,255,0.5);
  box-shadow: 5px 5px 15px rgba(0,0,0,0.5);
  border-radius: 50px;
  text-align: left;

  padding: 5% 5px;
  width: 200px;
  height: 600px;

}

.el-header
{
  background-color: rgba(255,255,255,0.5);
  box-shadow: 5px 5px 15px rgba(0,0,0,0.5);
  border-radius: 50px;
  text-align: center;
  margin: 0 1%;
  padding: 5% 0;
  width: 1000px;
  height: 50px;
}
.el-main
{
  margin: 0 1%;
  text-align: center;
  line-height: 10px;
  overflow-y: scroll;
}




#content{
  background-color: rgba(255,255,255,0.5);
  box-shadow: 5px 5px 15px rgba(0,0,0,0.5);
  border-radius: 50px;
  text-align: left;
  margin: 0 1%;
  padding: 5% 0;
  min-width: 50vh;
  min-height: 50vh;

}


</style>
