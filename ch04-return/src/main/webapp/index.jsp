<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#b1").click(function () {
                   // alert("888888");
                $.ajax({
                   // url:"return-ajax.do",
                   // url:"doStudentJsonObjectArray.do",
                    url:"returnStringData.do",
                    data:{
                        name:"zhangsan",
                        age:25
                    },
                    type:"post",
                    //dataType:"json",
                    success:function (resp) {
                        //resp：从服务器端返回的是json格式的字符串
                        //jquery会把字符串转为json对象，并赋值给resp形参。
                        //alert(resp)
                       // alert(resp.name + "    " + resp.age);
                       /* $.each(resp,function (i,n) {
                            alert(n.name + "   " + n.age);

                        })*/
                        alert("返回的是文本数据：" + resp);

                    }
                })
            })
        })
    </script>
</head>
<body>
    <p>处理器方法返回String表示视图名称</p>

    <form action="returnString-view.do" method="post">
        姓名：<input type="text" name="name"/><br>
        年龄：<input type="text" name="age"/><br>
        <input type="submit" value="提交参数"/>
    </form><br>

    <form action="returnString-view2.do" method="post">
        姓名：<input type="text" name="name"/><br>
        年龄：<input type="text" name="age"/><br>
        <input type="submit" value="提交参数"/>
    </form><br/><br/><br/>


    <input type="button" id="b1" value="发起Ajax请求"/>


</body>
</html>
