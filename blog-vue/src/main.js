import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from "./router";
import ElementPlus from 'element-plus';
import 'element-plus/theme-chalk/index.css';
import Layout from './components/home.vue';
import infiniteScroll from 'element-plus';
import * as ElIconModules from '@element-plus/icons-vue'
const app = createApp(App)
app.use(ElementPlus).use(router).use(Layout).use(infiniteScroll).mount('#app')

// 统一导入el-icon图标
for(let iconName in ElIconModules){
    app.component(iconName,ElIconModules[iconName])
}
