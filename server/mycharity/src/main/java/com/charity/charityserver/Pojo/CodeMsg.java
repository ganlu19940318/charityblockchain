package com.charity.charityserver.Pojo;

public class CodeMsg {
    public static int success_code = 0;
    public static String success_msg = "操作成功";

    public static int register_code = 600;
    public static String register_msg = "该用户名已经被注册";

    public static int chainregister_code = 601;
    public static String chainregister_msg = "链上注册失败";

    public static int login_code = 602;
    public static String login_msg = "用户名不存在";

    public static int password_code = 603;
    public static String password_msg = "密码错误";

    public static int relogin_code = 604;
    public static String relogin_msg = "请重新登录";

    public static int item_code = 605;
    public static String item_msg = "物品不存在";

    public static int itemuse_code = 606;
    public static String itemuse_msg = "物品无法使用";

    public static int farm_code = 607;
    public static String farm_msg = "土地编号不正确";

    public static int farmno_code = 608;
    public static String farmno_msg = "土地没有种子";

    public static int farmwait_code = 609;
    public static String farmwait_msg = "种子还没有成熟";

    public static int task_code = 610;
    public static String task_msg = "今日种子获取达到上限";

    public static int create_code = 611;
    public static String create_msg = "创建失败,重新操作一下即可";

    public static int param_code = 612;
    public static String param_msg = "入参有问题";

    public static int not_code = 613;
    public static String not_msg = "输入信息不正确";

}
