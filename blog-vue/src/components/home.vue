<template>
  <el-container style="padding-left: 20%;padding-right: 20%;" class="main">
    <el-header>
      <div id="login">
        <Handler id="handler"></Handler>
      </div>
    </el-header>

    <el-main style="overflow-y: hidden;background-color: #465453;border: solid 10px #39504c">

      <el-header>
        <div>
          <el-input type="text" style="width: 600px;margin: 10px" v-model="search"/>
          <el-button @click="searchBtn">搜索</el-button>
        </div>
      </el-header>

      <el-main style="overflow-y: hidden;padding: 0">
        <p v-for="(item) in tableDataTwo">{{item.name}}</p>



        <!-- 轮播图-->
        <el-carousel indicator-position="outside" type="card" style="margin: 0px">
          <el-carousel-item v-for="item in carouselList" :key="item">
            <el-image
                :src="item"
                fit="fill"></el-image>

          </el-carousel-item>
        </el-carousel>
      </el-main>
<!--      <el-footer>
        &lt;!&ndash; 页面内容&ndash;&gt;
        <div>
          <ul>
            <li><span v-html="link1"></span></li>
            <li><span v-html="link2"></span></li>
            <li><span v-html="link3"></span></li>
            <li><span v-html="link4"></span></li>
          </ul>
        </div>

      </el-footer>-->
        <el-header>
          <div class="he">
            <el-button >Java</el-button>
            <el-button >C</el-button>
            <el-button >C++</el-button>
            <el-button >Php</el-button>
            <el-button >Python</el-button>
            <el-button >Python</el-button>
            <el-button >Python</el-button>
            <el-button >Python</el-button>
            <el-button >Python</el-button>
            <el-button >Python</el-button>
            <el-button >Python</el-button>
            <el-button >Python</el-button>
            <el-button >Python</el-button>
            <el-button >Python</el-button>
            <el-button >Python</el-button>
          </div>
        </el-header>
        <el-main style="overflow-y: hidden">
        </el-main>

    </el-main>
  </el-container>
</template>

<script>
import {ref} from'vue'
import Handler from "../components/Handler.vue";
export default {

  name: "Home",
  components: {Handler},
  setup() {
    const search=ref('') //搜索框数据绑定
    const tableData = ref([
      {
        name:''
      },
      {
        name:'张三'
      },
      {
        name:'李四'
      },
      {
        name:'王五'
      },
      {
        name:'孙六'
      }
    ])
    const tableDataTwo=ref([])
    const searchBtn=()=>{

      let sea = search.value
      if (sea) {
        tableDataTwo.value = tableData.value.filter(function(a) { //通过鲁律数据赋值给新数组可实现重复搜索
          return Object.keys(a).some(function(key) {
            return String(a['name']).toLowerCase().indexOf(sea) > -1
          })
        })
      }
    }

    return {
      carouselList:[require("../assets/1.png"),require("../assets/2.png"),
        require("../assets/3.png"),require("../assets/4.png")],
      search,
      searchBtn,
      tableDataTwo,
      link1: "<a href='http://localhost:8080/#/blog/1+1'>1+1=?</a>",
      link2: "<a href='http://localhost:8080/#/blog/1+2'>1+2=?</a>",
      link3: "<a href='http://localhost:8080/#/blog/1+3'>1+3=?</a>",
      link4: "<a href='http://localhost:8080/#/blog/1+4'>1+4=?</a>",



    }

  },
  methods:{

  }
}
</script>

<style scoped>

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

.el-button{
  border: 0px;
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
  background-repeat: space,repeat-y;
}
</style>
