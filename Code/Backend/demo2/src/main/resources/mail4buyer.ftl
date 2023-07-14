<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="email code">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<!--邮箱验证码模板-->
<body>
<div style="background-color:#ECECEC; padding: 35px;">
    <table cellpadding="0" align="center"
           style="width: 800px;height: 100%; margin: 0px auto; text-align: left; position: relative; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0px 0px 5px; border-collapse: collapse; background-position: initial ; background-repeat: initial ;background:#fff;">
        <tbody>
        <tr>
            <th valign="middle"
                style="height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: RGB(148,0,211); background-color: RGB(148,0,211); border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;">
                <span style="color: rgb(255, 255, 255);  font-family: 微软雅黑; font-size: large; ">N-Market</span>
            </th>
        </tr>
        <tr>
            <td style="word-break:break-all">
                <div style="padding:25px 35px 40px; background-color:#fff;opacity:0.8;">

                    <h2 style="margin: 5px 0px; ">
                        <span style="line-height: 20px;  color: #333333; ">
                            <span style="line-height: 22px;  font-size: medium; ">
                                尊敬的用户：</span>
                        </span>
                    </h2>
                    <!-- 中文 -->
                    <p>您好！感谢您使用N-Market。您承诺购买并预约商品<span style="color: #d70d2b; ">《{0}》</span>，购买金额：<span style="color: #d70d2b; ">￥{3}</span>，预约时间段：<span style="color: #d70d2b; ">{1}</span></p>
                    <p>卖家联系方式为<span style="color: #d70d2b; ">{2}</span>，请双方在预约时间前尽快联系，以免影响双方信用，谢谢合作。</p><br>
                    <!-- 英文 -->
                    <h2 style="margin: 5px 0px; ">
                        <span style="line-height: 20px;  color: #333333; ">
                            <span style="line-height: 22px;  font-size: medium; ">
                                Dear user:</span>
                        </span>
                    </h2>
                    <p>Hello! Thanks for using N-Market, this is a e-mail for notification, your appointment has gone into effect, please check the message above and contact each other ASAP before the appointment, thank you for your cooperation.</p>
                    <div style="width:100%;margin:0 auto;">
                        <div style="padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;">
                            <p>N-Market团队</p>
                            <p>联系我们：南京市栖霞区仙林大道163号</p>
                            <br>
                            <p>此为系统邮件，请勿回复<br>
                                Please do not reply to this system email
                            </p>
                            <p>©Nanjing University</p>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>

