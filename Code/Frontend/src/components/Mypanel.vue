<template>
    <div class="my-panel w">
        <!-- 头部区域 -->
        <div class="header">
            <h2>
                {{ title }}
                <small>{{ subTitle }}</small>
            </h2>
        </div>
        <!-- 插槽 -->
        <slot name="right" />
        <!-- 默认插槽 -->
        <slot />
    </div>
</template>
  
<script>
export default {
    props: {
        title: {
            type: String,
            default: ""
        },
        subTitle: {
            type: String,
            default: ""
        }
    }
};
</script>
  
<style lang="less" scoped>
.my-panel {
    background-color: #fff;

    .header {
        padding: 40px 0;
        align-items: flex-end;
        display: flex;

        h2 {
            flex: 1;
            font-size: 32px;
            text-align: left;
            font-weight: normal;

            small {
                font-size: 16px;
                color: #999;
                margin-left: 10px;
            }
        }

    }
}
</style>