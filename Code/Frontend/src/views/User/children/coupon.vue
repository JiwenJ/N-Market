<template>
    <div>

        <y-shelf title="我的优惠">
            <template #content>
                <div style="padding: 50px 0;text-align: center">
                    <img src="../../../assets/images/no-search.png">
                    <br>
                    <span class="no-discount">您目前还没有优惠券</span>
                </div>
            </template>

        </y-shelf>
        <!-- <button @click=" this.delete()">ddd</button> -->


    </div>
</template>

<script>
import YShelf from "../../../components/shelf.vue"
import YButton from "../../../components/YButton.vue"
import { getStore, setStore, removeStore } from '@/utils/storage'
export default {
    data() {
        return {
            id: 1,
            key: "",
        }
    },
    components: {
        YShelf,
        YButton,
        getStore,
        setStore,
        removeStore
    },
    created() {
        this.do();
        console.log("HERE")

    },
    methods: {
        do() {
            setStore("key", "123")
            this.key = getStore("key")
        },
        delete() {
            removeStore("key");
            console.log("remove")
        },
    }
}
</script>

<style lang="less" scoped>
.support {
    line-height: 2em;
    font-size: 22px;
    color: #999;
}
</style>