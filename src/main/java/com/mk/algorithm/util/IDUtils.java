package com.mk.algorithm.util;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * 身份证解析工具类
 *
 * @author song.shi
 * @since 2018-07-06
 */
public class IDUtils {
    private static final Logger logger = LoggerFactory.getLogger(IDUtils.class);

    private static Map<String, String> areaMap;

    static {
        String code = "11=北京市,12=天津市,13=河北省,14=山西省,15=内蒙古自治区,21=辽宁省,22=吉林省,23=黑龙江省,31=上海市,32=江苏省,33=浙江省,34=安徽省,35=福建省,36=江西省,37=山东省,41=河南省,42=湖北省,43=湖南省,44=广东省,45=广西壮族自治区,46=海南省,50=重庆市,51=四川省,52=贵州省,53=云南省,54=西藏自治区,61=陕西省,62=甘肃省,63=青海省,64=宁夏回族自治区,65=新疆维吾尔自治区,1101=北京市,1102=北京市,1201=天津市,1202=天津市,1301=石家庄市,1302=唐山市,1303=秦皇岛市,1304=邯郸市,1305=邢台市,1306=保定市,1307=张家口市,1308=承德市,1309=沧州市,1310=廊坊市,1311=衡水市,1321=邯郸市,1322=邢台市,1323=石家庄市,1324=保定市,1325=张家口市,1326=承德市,1327=唐山市,1328=廊坊市,1329=沧州市,1330=衡水市,1401=太原市,1402=大同市,1403=阳泉市,1404=长治市,1405=晋城市,1406=朔州市,1407=晋中市,1408=运城市,1409=忻州市,1410=临汾市,1411=吕梁市,1501=呼和浩特市,1502=包头市,1503=乌海市,1504=赤峰市,1505=通辽市,1506=鄂尔多斯市,1507=呼伦贝尔市,1508=巴彦淖尔市,1509=乌兰察布市,1521=呼伦贝尔盟,1522=兴安盟,1525=锡林郭勒盟,1526=乌兰察布盟,1529=阿拉善盟,2101=沈阳市,2102=大连市,2103=鞍山市,2104=抚顺市,2105=本溪市,2106=丹东市,2107=锦州市,2108=营口市,2109=阜新市,2110=辽阳市,2111=盘锦市,2112=铁岭市,2113=朝阳市,2114=葫芦岛市,2201=长春市,2202=吉林市,2203=四平市,2204=辽源市,2205=通化市,2206=白山市,2207=松原市,2208=白城市,2224=延边朝鲜族自治州,2301=哈尔滨市,2302=齐齐哈尔市,2303=鸡西市,2304=鹤岗市,2305=双鸭山市,2306=大庆市,2307=伊春市,2308=佳木斯市,2309=七台河市,2310=牡丹江市,2311=黑河市,2312=绥化市,2327=大兴安岭地区,3101=上海市,3102=上海市,3201=南京市,3202=无锡市,3203=徐州市,3204=常州市,3205=苏州市,3206=南通市,3207=连云港市,3208=淮安市,3209=盐城市,3210=扬州市,3211=镇江市,3212=泰州市,3213=宿迁市,3301=杭州市,3302=宁波市,3303=温州市,3304=嘉兴市,3305=湖州市,3306=绍兴市,3307=金华市,3308=衢州市,3309=舟山市,3310=台州市,3311=丽水市,3401=合肥市,3402=芜湖市,3403=蚌埠市,3404=淮南市,3405=马鞍山市,3406=淮北市,3407=铜陵市,3408=安庆市,3410=黄山市,3411=滁州市,3412=阜阳市,3413=宿州市,3414=巢湖市,3415=六安市,3416=亳州市,3417=池州市,3418=宣城市,3501=福州市,3502=厦门市,3503=莆田市,3504=三明市,3505=泉州市,3506=漳州市,3507=南平市,3508=龙岩市,3509=宁德市,3601=南昌市,3602=景德镇市,3603=萍乡市,3604=九江市,3605=新余市,3606=鹰潭市,3607=赣州市,3608=吉安市,3609=宜春市,3610=抚州市,3611=上饶市,3701=济南市,3702=青岛市,3703=淄博市,3704=枣庄市,3705=东营市,3706=烟台市,3707=潍坊市,3708=济宁市,3709=泰安市,3710=威海市,3711=日照市,3712=莱芜市,3713=临沂市,3714=德州市,3715=聊城市,3716=滨州市,3717=荷泽市,4101=郑州市,4102=开封市,4103=洛阳市,4104=平顶山市,4105=安阳市,4106=鹤壁市,4107=新乡市,4108=焦作市,4109=濮阳市,4110=许昌市,4111=漯河市,4112=三门峡市,4113=南阳市,4114=商丘市,4115=信阳市,4116=周口市,4117=驻马店市,4201=武汉市,4202=黄石市,4203=十堰市,4204=沙市市,4205=宜昌市,4206=襄樊市,4207=鄂州市,4208=荆门市,4209=孝感市,4210=荆州市,4211=黄冈市,4212=咸宁市,4213=随州市,4228=恩施土家族苗族自治州,4290=直辖县级行政单位,4301=长沙市,4302=株洲市,4303=湘潭市,4304=衡阳市,4305=邵阳市,4306=岳阳市,4307=常德市,4308=张家界市,4309=益阳市,4310=郴州市,4311=永州市,4312=怀化市,4313=娄底市,4331=湘西土家族苗族自治州,4401=广州市,4402=韶关市,4403=深圳市,4404=珠海市,4405=汕头市,4406=佛山市,4407=江门市,4408=湛江市,4409=茂名市,4412=肇庆市,4413=惠州市,4414=梅州市,4415=汕尾市,4416=河源市,4417=阳江市,4418=清远市,4419=广东省东莞市,4420=广东省中山市,4451=潮州市,4452=揭阳市,4453=云浮市,4501=南宁市,4502=柳州市,4503=桂林市,4504=梧州市,4505=北海市,4506=防城港市,4507=钦州市,4508=贵港市,4509=玉林市,4510=百色市,4511=贺州市,4512=河池市,4513=来宾市,4514=崇左市,4521=南宁市,4600=海南省三亚各市县,4601=海口市,4602=三亚市,4690=省直辖县级行政单位,5001=重庆市,5002=重庆市,5003=市,5101=成都市,5103=自贡市,5104=攀枝花市,5105=泸州市,5106=德阳市,5107=绵阳市,5108=广元市,5109=遂宁市,5110=内江市,5111=乐山市,5113=南充市,5114=眉山市,5115=宜宾市,5116=广安市,5117=达州市,5118=雅安市,5119=巴中市,5120=资阳市,5132=阿坝藏族羌族自治州,5133=甘孜藏族自治州,5134=凉山彝族自治州,5201=贵阳市,5202=六盘水市,5203=遵义市,5204=安顺市,5222=铜仁地区,5223=黔西南布依族苗族自治州,5224=毕节地区,5226=黔东南苗族侗族自治州,5227=黔南布依族苗族自治州,5301=昆明市,5303=曲靖市,5304=玉溪市,5305=保山市,5306=昭通市,5307=丽江市,5308=思茅市,5309=临沧市,5323=楚雄彝族自治州,5325=红河哈尼族彝族自治州,5326=文山壮族苗族自治州,5328=西双版纳傣族自治州,5329=大理白族自治州,5331=德宏傣族景颇族自治州,5333=怒江傈僳族自治州,5334=迪庆藏族自治州,5401=拉萨市,5421=昌都地区,5422=山南地区,5423=日喀则地区,5424=那曲地区,5425=阿里地区,5426=林芝地区,6101=西安市,6102=铜川市,6103=宝鸡市,6104=咸阳市,6105=渭南市,6106=延安市,6107=汉中市,6108=榆林市,6109=安康市,6110=商洛市,6201=兰州市,6202=嘉峪关市,6203=金昌市,6204=白银市,6205=天水市,6206=武威市,6207=张掖市,6208=平凉市,6209=酒泉市,6210=庆阳市,6211=定西市,6226=陇南地区,6229=临夏回族自治州,6230=甘南藏族自治州,6301=西宁市,6321=海东地区,6322=海北藏族自治州,6323=黄南藏族自治州,6325=海南藏族自治州,6326=果洛藏族自治州,6327=玉树藏族自治州,6328=海西蒙古族藏族自治州,6401=银川市,6402=石嘴山市,6403=吴忠市,6404=固原市,6405=中卫市,6501=乌鲁木齐市,6502=克拉玛依市,6521=吐鲁番地区,6522=哈密地区,6523=昌吉回族自治州,6527=博尔塔拉蒙古自治州,6528=巴音郭楞蒙古自治州,6529=阿克苏地区,6530=克孜勒苏柯尔克孜自治州,6531=喀什地区,6532=和田地区,6540=伊犁哈萨克自治州,6542=塔城地区,6543=阿勒泰地区,6590=省直辖行政单位";
        Splitter.MapSplitter mapSplitter = Splitter.on(",").withKeyValueSeparator(Splitter.on("=").omitEmptyStrings().trimResults());
        areaMap = mapSplitter.split(code);
    }

    /**
     * <p>判断18位身份证的合法性</p>
     * <p>
     * 根据<中华人民共和国国家标准GB11643-1999>中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位地址码，八位出生日期码，三位标识码和一位校验码。
     * </p>
     * <p>地址码: 1~2位表示所在省份代码; 3~4位表示所在城市的代码; 5~6位表示所在区县代码</p>
     * <p>出生日期码: 7~14位表示出生年、月、日</p>
     * <p>标识码: 15~16位表示所在地的派出所的代码; 17位表示性别, 奇数表示男性，偶数表示女性</p>
     * <p>校验码: 18位表示校验码, 用来校验身份证的正确性, 校检码可以是0~9的数字，有时也用X表示</p>
     * <p>
     * 校验码的计算方法:<br/>
     * 1. 将前面的身份证号码17位数分别乘以不同的系数, 从第一位到第十七位的系数分别为: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 <br/>
     * 2. 将这17位数字和系数相乘的结果相加 <br/>
     * 3. 用加出来和除以11, 看余数是多少 <br/>
     * 4. 余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字, 其分别对应的最后一位身份证的号码为: 1 0 X 9 8 7 6 5 4 3 2 <br/>
     * 5. 通过上面得知如果余数是2, 就会在身份证的第18位数字上出现罗马数字的Ⅹ, 如果余数是10, 身份证的最后一位号码就是2 <br/>
     * </p>
     */
    public static boolean isValidID18(String id) {
        // id 不是18位为假
        if (null == id || 18 != id.length()) {
            return false;
        }
        // 获取前17位
        String id17 = id.substring(0, 17);
        // 获取第18位
        String id18Code = id.substring(17, 18);
        // 前17位是数字, 并且校验位相等为真
        return isDigit(id17) && id18Code.equalsIgnoreCase(getCheckCode(id17));
    }

    /**
     * 通过身份证号解析出出生年月日, 格式为: 19890101, 无法解析时返回空字符串
     */
    public static String getBirthday(String id) {
        if (isValidID(id)) {
            return getID18(id).substring(6, 14);
        } else {
            logger.error("{}不是合法的身份证号", id);
            return null;
        }
    }

    /**
     * 通过身份证号解析出年龄
     */
    public static String getAge(String id) {
        if (isValidID(id)) {
            // 使用LocalDate, 避免夏令时, 比如 19880410
            LocalDate date = LocalDate.parse(getID18(id).substring(6, 14), DateTimeFormatter.ofPattern("yyyyMMdd"));
            long years = date.until(LocalDate.now(), ChronoUnit.YEARS);
            return String.valueOf(years);
        } else {
            logger.error("{}不是合法的身份证号", id);
            return null;
        }
    }

    /**
     * 通过身份证号解析出性别, 0:女, 1:男
     */
    public static String getGender(String id) {
        if (isValidID(id)) {
            return String.valueOf(Integer.valueOf(getID18(id).substring(16, 17)) % 2);
        } else {
            logger.error("{}不是合法的身份证号", id);
            return null;
        }
    }

    /**
     * 通过身份证号前2位解析出省份
     */
    public static String getProvince(String id) {
        String province = null;
        if (isValidID(id)) {
            province = areaMap.get(id.substring(0, 2));
            if (Strings.isNullOrEmpty(province)) {
                logger.error("身份证{}的所在省未找到", id);
            }
        } else {
            logger.error("{}不是合法的身份证号", id);
        }
        return province;
    }

    /**
     * 通过身份证号前4位解析出城市, 由于历史原因, 有些城市已经不存在了, 所以有可能解析不出来, 会返回 null
     */
    public static String getCity(String id) {
        String city = null;
        if (isValidID(id)) {
            city = areaMap.get(id.substring(0, 4));
            if (Strings.isNullOrEmpty(city)) {
                logger.error("身份证{}的发证城市未找到", id);
            }
        } else {
            logger.error("{}不是合法的身份证号", id);
        }
        return city;
    }

    /**
     * 判断是否是有效的身份证号, 兼容18位和15位的身份证
     */
    public static boolean isValidID(String id) {
        if (null == id) {
            return false;
        }
        String id18 = null;
        if (18 == id.length()) {
            id18 = id;
        } else if (15 == id.length() && isDigit(id)) {
            id18 = convertID15ToID18(id);
        }
        return !Strings.isNullOrEmpty(id18) && isValidID18(id18);
    }

    /**
     * 把15位身份证号转成18位身份证号
     * <p>
     * 15位身份证号与18位身份证号的区别有两个: <br/>
     * 1. 出生日期只有6位, 年份前面少了两位(19), 比如 671218 对应到18位中应该是 19671218<br/>
     * 2. 没有最后一位校验位
     * </p>
     *
     * @return 转换后的18位身份证号, 当传入参数不合法时返回 null
     */
    public static String convertID15ToID18(String id15) {
        if (null == id15 || 15 != id15.length() || !isDigit(id15)) {
            logger.error("{}不是有效的15位身份证", id15);
            return null;
        }
        String id17 = id15.substring(0, 6) + "19" + id15.substring(6);
        String checkCode = getCheckCode(id17);
        return id17 + checkCode;
    }

    /**
     * 把一个有效的15位身份证转成18位, 若本身就是18位的, 直接返回
     */
    private static String getID18(String validID) {
        if (18 == validID.length()) {
            return validID;
        }
        return convertID15ToID18(validID);
    }

    /**
     * 判断字符串是否全是由阿拉伯数字 0~9 组成
     */
    public static boolean isDigit(String str) {
        return null != str && str.matches("^[0-9]+$");
    }

    /**
     * 通过身份证的前17位计算得到最后一位校验码
     */
    private static String getCheckCode(String id17) {
        // 系数
        int[] coefficient = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            int code = Integer.valueOf(String.valueOf(id17.charAt(i)));
            sum += code * coefficient[i];
        }
        int mod = sum % 11;
        // 校验码
        String[] checkCode = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
        return checkCode[mod];
    }
}