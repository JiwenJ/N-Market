package com.example.demo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.ServerConst;
import com.example.demo.entity.Good;
import com.example.demo.entity.User;
import com.example.demo.mapper.GoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/Good")
public class GoodController {
    @Value("${prop.backendIp}")
    private String backendIp;
//    private int step = 100;
    @Autowired
    private GoodMapper goodMapper;
    //------------------轮播图--------------------- OK
    @GetMapping("Banner")
    public List<HashMap> getBanner()
    {
        System.out.println("HERE IS BANNER");
        int num = 4;
        List<HashMap> list =new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/banner/";
            String pic_name =  serverconst + Integer.toString(i)+".png";
            dict.put("img",pic_name);
//            System.out.println(pic_name);
            list.add(dict);
        }
        return list;
    }
    //------------------轮播图---------------------

    @RequestMapping("updateGoodStatus")
    public String updateGoodStatus(@RequestParam Map<String, String> params) {
        System.out.println("updateGoodStatus");
        int id = Integer.parseInt(params.get("id"));
        int uid = Integer.parseInt(params.get("uid"));
        int status = Integer.parseInt(params.get("status"));
        Good good = new Good();
        good.setId(id);
        good.setUid(uid);
        good.setStatus(status);
        goodMapper.updateById(good);
        return "OK";
    }


    @RequestMapping("insert")
    public String insert(@RequestParam Map<String, String> params){
        System.out.println("--------insert-------");
        Good good = new Good();
        int uid = Integer.parseInt(params.get("uid"));
        String goodname=params.get("name");
        int category= Integer.parseInt(params.get("category"));
        String comment=params.get("comment");
        String email=params.get("email");
        String price=params.get("price");
        String picurl1=params.get("picurl1");
        String picurl2=params.get("picurl2");
        String picurl3=params.get("picurl3");
        String time = params.get("time");
        good.setName(goodname);
        good.setEmail(email);
        good.setUid(uid);
        good.setTime(time);
        good.setComment(comment);
        good.setPrice(price);
        good.setCategory(Integer.valueOf(category));
        good.setPicurl1(picurl1);
        good.setPicurl2(picurl2);
        good.setPicurl3(picurl3);
        System.out.println(good);
        goodMapper.insert(good);
        return "OK";
    }

    @RequestMapping("getAllItem")
    public List<Good> getAllItem(){
        List<Good> goods = goodMapper.selectList(null);
        System.out.println(goods);
        return goods;

    }
    //根据分类查找商品category
    @RequestMapping("getByCategory")
    public List<Good> getByCategory(@RequestParam("category") int cate){
        System.out.println(cate);
        QueryWrapper<Good> goodQueryWrapper = new QueryWrapper<>();
        goodQueryWrapper.eq("category",cate);
        List<Good> goodList = goodMapper.selectList(goodQueryWrapper);
        System.out.println(goodList);
        return goodList;
    }
    @RequestMapping("getByName")
    public List<Good> getByName(@RequestParam("productname") String name){
        System.out.println(name);
        QueryWrapper<Good> goodQueryWrapper = new QueryWrapper<>();
        goodQueryWrapper.eq("name",name);
        List<Good> goodList = goodMapper.selectList(goodQueryWrapper);
        System.out.println(goodList);
        return goodList;
    }
    //根据用户id查找商品用户发布的商品
    @RequestMapping("getByUid")
    public List<Good> getByUid(@RequestParam Map<String, String> params) throws ParseException {
        int uid = Integer.parseInt(params.get("id"));
        System.out.println(uid);
        QueryWrapper<Good> goodQueryWrapper = new QueryWrapper<>();
        goodQueryWrapper.eq("uid",uid);
        List<Good> goodList = goodMapper.selectList(goodQueryWrapper);
        System.out.println(goodList);
        return goodList;
    }




//    @RequestMapping("detailslist")
////    @RequestParam(name = "productId", required = false)
//    public List<Good> getDetailslist(@RequestParam(name = "productId", required = false)String category) {
//        System.out.println(category);
//        System.out.println("detailslist");
//        QueryWrapper<Good> goodQueryWrapper = new QueryWrapper<>();
//        goodQueryWrapper.eq("category", category);
//        List<Good> goodList = goodMapper.selectList(goodQueryWrapper);
//        System.out.println(goodList);
//        return goodList;
//    }
    @RequestMapping("detailslist")
//    @RequestParam(name = "productId", required = false)
    public List<Good> getDetailslist(@RequestParam(name = "productname", required = false)String name) {
        System.out.println(name);
        System.out.println("detailslist");
        QueryWrapper<Good> goodQueryWrapper = new QueryWrapper<>();
        goodQueryWrapper.eq("name", name);
        List<Good> goodList = goodMapper.selectList(goodQueryWrapper);
        System.out.println(goodList);
        return goodList;
    }


    @RequestMapping("GoodsItem")
    public List<HashMap> getGoodsItem(@RequestParam Map<String, String> params)
    {
        System.out.println("---GoodsItem------");
        System.out.println("-------");
        String subject = params.get("interest");
        System.out.println("-------");
        System.out.println(subject);
        int num = 8;
        int j = 0;
        List<HashMap> list =new ArrayList<>();
        if(Objects.equals(subject, "数学")){
        System.out.println("---GoodsItem---Math----");
        List<String> firstNameList =new ArrayList<String>(Arrays.asList("微积分","军事教程","弹性力学","概率论与数理统计","高等代数","高级英语口语辩论基础与实训","工程地质学","计算机操作系统"));
        for (int i = 1; i <= num; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/goodsitem/math/";
            String pic_name =  serverconst + Integer.toString(i)+".jpg";
            dict.put("images",pic_name);
            dict.put("name",firstNameList.get(j));
            dict.put("price","100");
            list.add(dict);
            j++;
        }
        return list;
        }
        else if(Objects.equals(subject, "计算机")){
            System.out.println("---GoodsItem----computer--");
            List<String> firstNameList =new ArrayList<String>(Arrays.asList("微积分","军事教程","弹性力学","概率论与数理统计","高等代数","高级英语口语辩论基础与实训","工程地质学","计算机操作系统"));
            for (int i = 1; i <= num; i++) {
                HashMap<String, String> dict = new HashMap<>();
                String serverconst = backendIp+"/images/goodsitem/computer/";
                String pic_name =  serverconst + Integer.toString(i)+".jpg";
                dict.put("images",pic_name);
                dict.put("name",firstNameList.get(j));
                dict.put("price","100");
                list.add(dict);
                j++;
            }
            return list;
        }
        else {
            System.out.println("---GoodsItem---NO---");
            List<String> firstNameList =new ArrayList<String>(Arrays.asList("微积分","军事教程","弹性力学","概率论与数理统计","高等代数","高级英语口语辩论基础与实训","工程地质学","计算机操作系统"));
            for (int i = 1; i <= num; i++) {
                HashMap<String, String> dict = new HashMap<>();
                String serverconst = backendIp+"/images/goodsitem/";
                String pic_name =  serverconst + Integer.toString(i)+".jpg";
                dict.put("images",pic_name);
                dict.put("name",firstNameList.get(j));
                dict.put("price","100");
                list.add(dict);
                j++;
            }
            return list;

        }
    }


    @GetMapping("GoodsItemLeft")
    public List<HashMap> getGoodsItemLeft()
    {
        System.out.println("GoodsItemLeft");
        int num = 1;
        int j = 0;
        List<HashMap> list =new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/goodsitemleft/";
            String pic_name =  serverconst + Integer.toString(1)+".jpg";
            dict.put("images",pic_name);
            list.add(dict);
            j++;
        }
        return list;
    }



    @GetMapping("TongXiuList_shuxue")
    public List<HashMap> getTongXiu()
    {
        int goodsurl = 100;
        System.out.println("TongXiuList_shuxue");
        int num = 4;
        int j = 0;
        List<HashMap> list =new ArrayList<>();
        List<String> firstNameList =new ArrayList<String>(Arrays.asList("微积分","大学数学","高等代数","概率论与数理统计"));
        for (int i = 1; i <= num; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/TongXiu/";
            String picUrl =  serverconst + Integer.toString(i)+".jpg";
            dict.put("picUrl",picUrl);
            dict.put("name",firstNameList.get(j));
            dict.put("goodsUrl", String.valueOf(goodsurl));
            list.add(dict);
            j++;
            goodsurl++;
        }
        return list;
    }
    @GetMapping("TongXiuList_yingyu")
    public List<HashMap> getyingyu()
    {
        int goodsurl = 100;
        System.out.println("TongXiuList_yingyu");
        int num = 4;
        int j = 0;
        List<HashMap> list =new ArrayList<>();
        List<String> firstNameList =new ArrayList<String>(Arrays.asList("高级英语口语辩论基础与实训","大学英语","英语学术论文阅读与写作","汉英翻译学基础理论与实践"));
        for (int i = num+1; i <= num*2; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/TongXiu/";
            String picUrl =  serverconst + Integer.toString(i)+".jpg";
            dict.put("picUrl",picUrl);
            dict.put("name",firstNameList.get(j));
            dict.put("goodsUrl","100");
            list.add(dict);
            j++;
        }
        return list;
    }
    @GetMapping("TongXiuList_zhengzhi")
    public List<HashMap> getzhengzhi()
    {
        System.out.println("TongXiuList_zhengzhi");
        int num = 4;
        int j = 0;
        List<HashMap> list =new ArrayList<>();
        List<String> firstNameList =new ArrayList<String>(Arrays.asList("大学军事理论教程","近代史","毛泽东思想和中国特色社会主义理论体系概论","思想道德与法治"));
        for (int i = 2*num+1; i <= num*3; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/TongXiu/";
            String picUrl =  serverconst + Integer.toString(i)+".jpg";
            dict.put("picUrl",picUrl);
            dict.put("name",firstNameList.get(j));
            dict.put("goodsUrl","100");
            list.add(dict);
            j++;
        }
        return list;
    }
    @GetMapping("KeWaiList_xiaoshuo")
    public List<HashMap> getKeWaiList_xiaoshuo()
    {
        int num = 4;
        int j = 0;
        List<HashMap> list =new ArrayList<>();
        List<String> firstNameList =new ArrayList<String>(Arrays.asList("边城","变形记","宠物猫的反击战","三体"));
        for (int i = 1; i <= num; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/kewai/";
            System.out.println(serverconst);
            String picUrl =  serverconst + Integer.toString(i)+".jpg";
            dict.put("picUrl",picUrl);
            dict.put("name",firstNameList.get(j));
            dict.put("goodsUrl","100");
            list.add(dict);
            j++;
        }
        return list;
    }
    @GetMapping("KeWaiList_manhua")
    public List<HashMap> getKeWaiList_manhua()
    {
        System.out.println("KeWaiList_manhua");
        int num = 4;
        int j = 0;
        List<HashMap> list =new ArrayList<>();
        List<String> firstNameList =new ArrayList<String>(Arrays.asList("巴黎城记","漫画龙纹身的女孩","漫画名侦探柯南漆黑的追踪者","漫画千与千寻"));
        for (int i = num+1; i <= num*2; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/kewai/";
            String picUrl =  serverconst + Integer.toString(i)+".jpg";
            dict.put("picUrl",picUrl);
            dict.put("name",firstNameList.get(j));
            dict.put("goodsUrl","100");
            list.add(dict);
            j++;
        }
        return list;
    }



    @GetMapping("ZhuanYeList_dike")
    public List<HashMap> getdike()
    {
        System.out.println("ZhuanYeList_dike");
        int num = 4;
        int j = 0;
        List<HashMap> list =new ArrayList<>();
        List<String> firstNameList =new ArrayList<String>(Arrays.asList("工程地质学","普通地质学","岩石力学","弹性力学"));
        for (int i = 1; i <= num; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/ZhuanYe/";
            String picUrl =  serverconst + Integer.toString(i)+".jpg";
            dict.put("picUrl",picUrl);
            dict.put("name",firstNameList.get(j));
            dict.put("goodsUrl","100");
            list.add(dict);
            j++;
        }
        return list;
    }


    @GetMapping("ZhuanYeList_jike")
    public List<HashMap> getjike()
    {
        System.out.println("ZhuanYeList_jike");
        int num = 4;
        int j = 0;
        List<HashMap> list =new ArrayList<>();
        List<String> firstNameList =new ArrayList<String>(Arrays.asList("入门到精通","程序设计基础","操作系统","计算机基础"));
        for (int i = num+1; i <= num*2; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/ZhuanYe/";
            String picUrl =  serverconst + Integer.toString(i)+".jpg";
            dict.put("picUrl",picUrl);
            dict.put("name",firstNameList.get(j));
            dict.put("goodsUrl","100");
            list.add(dict);
            j++;
        }
        return list;
    }


    @GetMapping("MianFeiList_kewai")
    public List<HashMap> getMianFeiList_kewai()
    {
        System.out.println("MianFeiList_kewai");
        int num = 4;
        int j = 0;
        List<HashMap> list =new ArrayList<>();
        List<String> firstNameList =new ArrayList<String>(Arrays.asList("漫画千与千寻","十角馆事件","异世界舅舅","蜘蛛侠漫画史"));
        for (int i = num+1; i <= num*2; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/mianfei/";
            String picUrl =  serverconst + Integer.toString(i)+".jpg";
            dict.put("picUrl",picUrl);
            dict.put("name",firstNameList.get(j));
            dict.put("goodsUrl","100");
            list.add(dict);
            j++;
        }
        return list;
    }

    @GetMapping("MianFeiList_jiaoke")
    public List<HashMap> getMianFeiList_jiaoke()
    {
        System.out.println("MianFeiList_jiaoke");
        int num = 4;
        int j = 0;
        List<HashMap> list =new ArrayList<>();
        List<String> firstNameList =new ArrayList<String>(Arrays.asList("量子力学","普通生物学","普通物理学","有机固体物理"));
        for (int i = 1; i <= num; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/mianfei/";
            String picUrl =  serverconst + Integer.toString(i)+".jpg";
            dict.put("picUrl",picUrl);
            dict.put("name",firstNameList.get(j));
            dict.put("goodsUrl","100");
            list.add(dict);
            j++;
        }
        return list;
    }
    @GetMapping("KaoYanList_shuxue")
    public List<HashMap> getKaoYanList_shuxue()
    {
        System.out.println("KaoYanList_shuxue");
        int num = 4;
        int j = 0;
        List<HashMap> list =new ArrayList<>();
        List<String> firstNameList =new ArrayList<String>(Arrays.asList("考研数学二十讲","考研数学辅导","考研数学复习指南","考研数学题典"));
        for (int i = 1; i <= num; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/kaoyan/";
            String picUrl =  serverconst + Integer.toString(i)+".jpg";

            dict.put("picUrl",picUrl);
            dict.put("name",firstNameList.get(j));
            dict.put("goodsUrl","100");

            list.add(dict);
            j++;
        }
        return list;
    }
    @GetMapping("KaoYanList_zhengzhi")
    public List<HashMap> getKaoYanList_zhengzhi()
    {
        System.out.println("KaoYanList_zhengzhi");
        int num = 4;
        int j = 0;
        List<HashMap> list =new ArrayList<>();
        List<String> firstNameList =new ArrayList<String>(Arrays.asList("考研政治核心考点","考研政治狂背清单","考研政治力解考点","考研政治强化冲刺800题"));
        for (int i = 1+num; i <= num*2; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/kaoyan/";
            String picUrl =  serverconst + Integer.toString(i)+".jpg";
            System.out.println(picUrl);

            dict.put("picUrl",picUrl);
            dict.put("name",firstNameList.get(j));
            dict.put("goodsUrl","100");

            list.add(dict);
            j++;
        }
        return list;
    }


    @GetMapping("hotlist1")
    public List<HashMap> gethotlist1()
    {
        System.out.println("hotlist1");
        int num = 4;
        int j = 0;
        List<HashMap> list =new ArrayList<>();
        List<String> firstNameList = new ArrayList<>(Arrays.asList("微积分", "毛概", "大学英语", "近代史"));
        for (int i = 1; i <= num; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/hotlist1/";
            String picUrl =  serverconst + Integer.toString(i)+".jpg";
            //----------------------
            dict.put("listPicUrl",picUrl);
            dict.put("name",firstNameList.get(j));
            dict.put("retailPrice","100");
            dict.put("goodsUrl","100");
            //----------------------
            list.add(dict);
//            System.out.println(picUrl);
            j++;
        }

        return list;
    }

    @GetMapping("hotlist2")
    public List<HashMap> gethotlist2()
    {
        System.out.println("hotlist2");
        int num = 4;
        int j = 0;
        List<HashMap> list =new ArrayList<>();
        List<String> firstNameList =new ArrayList<String>(Arrays.asList("入门到精通","操作系统","计算机基础","普通地质学"));
        for (int i = 1; i <= num; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/hotlist2/";
            String picUrl =  serverconst + Integer.toString(i)+".jpg";
//            System.out.println(picUrl);

            dict.put("listPicUrl",picUrl);
            dict.put("name",firstNameList.get(j));
            dict.put("retailPrice","100");
            dict.put("goodsUrl","100");

            list.add(dict);
            j++;
        }

        return list;
    }
    @GetMapping("hotlist3")
    public List<HashMap> gethotlist3()
    {
        System.out.println("hotlist3");
        int num = 4;
        int j = 0;
        List<HashMap> list =new ArrayList<>();
        List<String> firstNameList =new ArrayList<String>(Arrays.asList("千与千寻","边城","变形记","三体"));
        for (int i = 1; i <= num; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = backendIp+"/images/hotlist3/";
            String picUrl =  serverconst + Integer.toString(i)+".jpg";
//            System.out.println(picUrl);

            dict.put("listPicUrl",picUrl);
            dict.put("name",firstNameList.get(j));
            dict.put("retailPrice","100");
            dict.put("goodsUrl","100");

            list.add(dict);
            j++;
        }

        return list;
    }


}
