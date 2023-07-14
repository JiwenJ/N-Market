<template>
    <div class="home_bottom">
        <photo-wall :title="'照片墙'" :photos="photos"></photo-wall>
    </div>
</template>

<script>
import PhotoWall from "@/views/Home/PhotoWall.vue";
export default {
    components: {
        PhotoWall,
    },
    data() {
        return {
            photos: [
                {
                    src: "https://picsum.photos/500/500?random=1",
                    alt: "Random Image 1",
                },
                {
                    src: "https://picsum.photos/500/500?random=2",
                    alt: "Random Image 2",
                },
                {
                    src: "https://picsum.photos/500/500?random=3",
                    alt: "Random Image 3",
                },
                {
                    src: "https://picsum.photos/500/500?random=4",
                    alt: "Random Image 4",
                },
                // {
                //     src: "https://picsum.photos/500/500?random=5",
                //     alt: "Random Image 5",
                // },
                // {
                //     src: "https://picsum.photos/500/500?random=6",
                //     alt: "Random Image 6",
                // },
            ],
        };
    },
};
</script>
<style lang="less" scoped>
.home_bottom {
    margin-top: 70px;
    padding: 75px 0 0 0;
    width: 1100;
    background-color: #f6f1f1;
}
</style>