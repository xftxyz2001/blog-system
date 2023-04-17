<template>
  <el-container style="height: auto" class="main">
    <el-header>
      <div id="login">
        <Handler id="handler"></Handler>
      </div>
    </el-header>

    <el-container>
      <el-aside width="400px" style="height: 950px;">
        <el-tabs v-model="activeName" stretch class="demo-tabs" style="padding: 20px" @tab-click="handleClick">
          <el-tab-pane name="first">
            <template #label>
              <div>
                <img src="../assets/svg/热门.svg" alt="">
                <span style="font-size: 25px;color: white;margin-left: 20px">热门</span>
              </div>
            </template>

            <el-card v-for="hot in hotBlog" shadow="always" class="asideCard">
              <template #header>
                <div class="hotBlog" @click="enterBlog(hot.bid)">{{sliceStr(hot.btitle,20)}}</div>
              </template>
              <div class="hotBlog" @click="enterBlog(hot.bid)">{{sliceStr(hot.bcontent,30)}}</div>
            </el-card>
          </el-tab-pane>
          <el-tab-pane name="second">
            <template #label>
              <img src="../assets/svg/最新.svg" alt="">
              <span style="font-size: 25px;color: white;margin-left: 20px">最新</span>
            </template>
            <el-card v-for="rec in recentBlog" shadow="always" class="asideCard">
              <template #header>
                <div class="hotBlog" @click="enterBlog(rec.bid)">{{sliceStr(rec.btitle,20)}}</div>
              </template>
              <div class="hotBlog" @click="enterBlog(rec.bid)">{{sliceStr(rec.bcontent,30)}}</div>
            </el-card>
          </el-tab-pane>
        </el-tabs>

      </el-aside>

    <el-main  class="zhuti" style="overflow-y: hidden">
      <el-header>
        <div>
          <el-input type="text" style="width: 600px;margin: 10px" v-model="search"/>
          <el-button @click="searchBtn">搜索</el-button>
        </div>
      </el-header>

      <el-main style="overflow-y: hidden;padding: 0">
        <!-- 轮播图-->
        <el-carousel :interval="4000" type="card" height="15vw" arrow="hover">
          <el-carousel-item v-for="item in carouseData" :key="item">
            <img :src="item.url" alt="" style="width: 100%; height: auto;"/>
          </el-carousel-item>
        </el-carousel>

        <el-header>
          <div class="he">
            <el-button >Java</el-button>
            <el-button >C</el-button>
            <el-button >C++</el-button>
            <el-button >Php</el-button>
            <el-button >Python</el-button>
            <el-button >日常</el-button>
            <el-button >美食</el-button>
            <el-button >生活</el-button>
            <el-button >风景</el-button>
            <el-button >游戏</el-button>
            <el-button >新闻</el-button>
            <el-button >热门</el-button>
          </div>
        </el-header>
      </el-main>

      <div v-infinite-scroll="getBlog" class="infinite-list" style="overflow: auto" infinite-scroll-distance="1">
      <el-main style="overflow-y: hidden">
          <el-row>
            <el-col v-for="blog in BlogData"
                    :key="blog.bid"
                    :span="12"
                    style="margin: 0"
                    class="infinite-list-item">
                <el-card :body-style="{ padding: '0px'}" shadow="always" class="blog">
                  <template #header class="card-headerr">
                    <div class="card-header">
                      <span class="blogContent" @click="enterBlog(blog.bid)">{{sliceStr(blog.btitle,20)}}</span>
                    </div>
                  </template>
                  <span class="textcontent" @click="enterBlog(blog.bid)">{{sliceStr(blog.bcontent,30)}}</span>
                  <div style="padding: 10px;">
                    <div style="float: left"><el-button text type="primary" plain style="background-color: #30484b">{{blog.uname}}</el-button></div>
                    <div class="bottom">
                      <el-button text type="primary" class="button" icon="Comment" style="background-color: #30484b"></el-button>
                      <el-button text type="primary" class="button" icon="ArrowUp" style="background-color: #30484b"></el-button>
                      <el-button text type="primary" class="button" icon="Star" style="background-color: #30484b"></el-button>
                    </div>
                  </div>
                </el-card>
            </el-col>
          </el-row>
        </el-main>
      </div>
    </el-main>

    </el-container>
  </el-container>
</template>
<script setup>
import {computed, onMounted, ref} from 'vue'
import Handler from "../components/Handler.vue";
import axios from "axios";
import {ElMessage} from "element-plus";

const activeName = ref('first')
const carouseData = [
  {url: require("../assets/gangtie.jpg")},
  {url: require("../assets/dd.jpeg")},
  {url: require("../assets/123456.jpeg")},
  {url: require("../assets/4455.jpg")},
]
let cur = 1;
let i = 0;
let BlogData = ref({});
let hotBlog = ref({});
let recentBlog = ref({});
let index = 0;

const sliceStr= computed(()=>{
  return function (val,len){
    return val.length>len?val.slice(0,len)+"...":val
  }
})
function getBlog(){
  axios({
    method:'get',
    params:{
      cur:cur++
    },
    url:'/getBlog',
  }).then(res=>{
    for (let j = i; j <i+2; j++) {
      BlogData.value[j]=res.data.data.records[j-i];
    }
    i=i+2;
  })
}
function getHotBlog(){
  axios({
    method:'get',
    params:{
      num:5,
    },
    url:'/getHotBlog',
  }).then(res=>{
    for (let k = 0; k < 5; k++) {
      hotBlog.value[k]=res.data.data[k];
    }
  })
}
function getRecentBlog(){
  axios({
    method:'get',
    params:{
      num:5,
    },
    url:'/getRecentBlog',
  }).then(res=>{
    for (let k = 0; k < 5; k++) {
      recentBlog.value[k]=res.data.data[k];
    }
  })
}
function enterBlog(bid){
 /* window.location.href=*/
  ElMessage.success("跳转到博客界面")
  window.location.href="/#/blog/:bid";
}
onMounted(()=>{
  getRecentBlog()
  getHotBlog();
  getBlog();
})

</script>



<style scoped>
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
.hotBlog{
  color: white;
  cursor: pointer;
}
.asideCard{
  margin: 4%;
  height: 135px;
  background-color: #30484b;
  border: 0;
  border-radius: 10px;
}
.asideCard /deep/ .el-card__body{
  padding: 10px 10px 20px;
}
.asideCard /deep/ .el-card__header{
  padding: 10px 10px 10px 20px;
}
.zhuti{
  background-color: #465453;
  border: solid 10px #39504c;
  padding: 0;
  margin-left: 10px;
  margin-right: 20%;
}
.textcontent{
  display: flex;
  margin-top: 10px;
  margin-left: 10px;
  color: white;
  cursor: pointer;
}
.blogContent{
  cursor: pointer;
}
.card-header {
  float: left;
  color: white;
}
.el-container{
  padding: 0;
  /*外部间距也是如此设置*/
  margin: 0;
  /*统一设置高度为100%*/
  height: 100%;
  width: 100%;
}

.el-main {

  text-align: center;
  line-height: 10px;
  overflow-y: scroll;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.el-carousel__item h3 {
  display: flex;
  color: #475669;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}
ul {
  width: 1250px;
  height: 600px;
  margin: 0 auto;

}
li {
  width: 600px;
  line-height: 50px;
}

.he{
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 0px;
}
.main{
  background-image: url("@/assets/ddd.jpeg");
  background-repeat: round;
}

.bottom {
  line-height: 12px;
  display: inline;
  justify-content: space-between;
  align-items: center;
  float: right;
}

.button {
  padding: 0;
  min-height: auto;
}
.blog{
  background-color: #30484b;
  border: 0;
  margin-bottom: 10px;
  margin-right: 10px;
}
.infinite-list{
  height: 450px;
}
</style>
