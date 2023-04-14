import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from "./router";
import ElementPlus from 'element-plus';
import 'element-plus/theme-chalk/index.css';
import Layout from './components/home.vue';
import infiniteScroll from 'element-plus';
import axios from 'axios'
import * as ElIconModules from '@element-plus/icons-vue'
const app = createApp(App)
app.use(ElementPlus).use(router).use(Layout).use(infiniteScroll).mount('#app')

axios.defaults.baseURL = window.location.origin;
// axios.defaults.baseURL = http://8.130.81.23:8081;

/*
axios.get('/api').then(res => {
    var r = res.data;
    if (r.flag) {
        console.log(r.data);
    } else {
        ElMessage.warning(r.msg);
    }
});
*/
// 统一导入el-icon图标
for (let iconName in ElIconModules) {
    app.component(iconName, ElIconModules[iconName])
}
