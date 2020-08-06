package com.happy.live.common.office.excel;

/**
 * 类名:DataFormat <tb>
 * 描述:请输入类描述  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2018/12/3 16:12 <tb>
 */
public interface DataFormat<In,Out> {
    public Out format(In value) ;
}
