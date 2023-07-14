import Vue from 'vue';
import { createApp } from 'vue';
import App from './App.vue';
import router from './router/index.js';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import store from './store/index';
// import './mock';
import axios from 'axios';
import * as ElIcon from '@element-plus/icons-vue'
import { Button, Pagination, Checkbox, Icon, Autocomplete, Loading, Message, Notification, Steps, Step, Table, TableColumn, Input, Dialog, Select, Option } from 'element-plus'
const app = createApp(App);
Object.keys(ElIcon).forEach((key) => {
    app.component(key, ElIcon[key])})
app.use(router).use(ElementPlus).use(store);
app.mount('#app')