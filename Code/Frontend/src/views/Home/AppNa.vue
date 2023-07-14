<template>
    <div>
        <ul class="app-header-nav">
            <li>
                <RouterLink class="nav-item" to="/TongXiu">通修课程二手书</RouterLink>
            </li>
            <li>
                <RouterLink class="nav-item" to="/ZhuanYe">专业课类二手书</RouterLink>
            </li>
            <li>
                <RouterLink class="nav-item" to="/KeWai">课外类二手书</RouterLink>
            </li>
            <li>
                <RouterLink class="nav-item" to="/KaoYan">考研材料</RouterLink>
            </li>
            <li>
                <RouterLink class="nav-item" to="/MianFei">免费专区</RouterLink>
            </li>
        </ul>
    </div>
</template>

<script>
export default {};
</script>

<style lang="less" scoped>
.app-header-nav {
    list-style: none;
    position: relative;
    clear: both;
    // 使其在最上面一层
    z-index: 999;
    // margin: auto;
    // height: 190px;
    // background-color: rgb(255, 255, 255);
    display: 0 flex;

    li {
        position: relative;
        left: 140px;
        float: left;
        // 每个标签之间的距离，先上下后左右
        padding: 0 80px;
        font-weight: bold;
        height: 40px;
        line-height: 40px;
        white-space: nowrap;

        // .router-Link{
        //     padding-bottom: 10px;
        //     font-size: 15px;
        //     color: #090909;
        //     text-decoration: none;
        //     font-weight: bold;
        // }
        // 选中高亮
        .router-link-active {
            color: #9307e4;
            border-bottom: 2px solid#9307e4;
        }

        .nav-item {
            padding-bottom: 10px;
            font-size: 15px;
            color: #090909;
            text-decoration: none;
            font-weight: bold;

            // 划过效果
            &:hover {
                color: #9307e4;
                border-bottom: 2px solid#9307e4;
            }
        }

    }

    .header-layer {
        position: absolute;
        left: 130px;
        top: 100px;
        background-color: #fff;
        box-shadow: 0 0 5px #ccc;
        padding: 10px;
        width: 1150px;
        height: 130px;

        ul {
            li {
                padding: 5px;
                list-style-type: none;
                left: 0px;
                height: 100px;

                img {
                    width: 10px;
                    height: 10px;
                    text-align: center;
                }

                &:hover {
                    background-color: #ccc;
                }
            }
        }
    }
}
</style>