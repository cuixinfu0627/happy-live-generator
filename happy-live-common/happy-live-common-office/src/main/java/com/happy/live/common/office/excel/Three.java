package com.happy.live.common.office.excel;

/**
 * 类名:Three <tb>
 * 描述:请输入类描述  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2018/12/3 16:12 <tb>
 */
public class Three<One,Two,Three> {
    private One one ;
    private Two two ;
    private Three three ;

    public Three() {
    }

    public Three(One one, Two two, Three three) {
        this.one = one;
        this.two = two;
        this.three = three;
    }

    public One getOne() {
        return one;
    }

    public void setOne(One one) {
        this.one = one;
    }

    public Two getTwo() {
        return two;
    }

    public void setTwo(Two two) {
        this.two = two;
    }

    public Three getThree() {
        return three;
    }

    public void setThree(Three three) {
        this.three = three;
    }
}