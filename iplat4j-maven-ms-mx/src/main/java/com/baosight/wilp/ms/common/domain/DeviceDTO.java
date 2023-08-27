package com.baosight.wilp.ms.common.domain;


import java.io.Serializable;
import java.util.List;

/**
 * @description: 采集数据设备类
 * @author: panlingfeng
 * @createDate: 2021/8/5 5:04 下午
 */
public class DeviceDTO implements Serializable {
    private Integer rsn;
    private String sn; //网关设备SN
    private Integer handle; //网关设备句柄
    private Integer ver; //网关工程版本，每次工程变化，该版本号加1
    private String name; //网关名称
    private String desc; //网关描述
    private Integer ps_sndmod; //推送模式,0-变化推送，1-定时推送，2-定时变化推送
    private Integer ps_sndspn; //推送周期，单位秒
    private Integer ps_rtcall; //总召周期，单位秒
    private List<AttrDTO> _attrs; //属性
    private List<SensorDTO> sensors; //传感器

    public DeviceDTO() {
    }

    public Integer getRsn() {
        return rsn;
    }

    public void setRsn(Integer rsn) {
        this.rsn = rsn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getHandle() {
        return handle;
    }

    public void setHandle(Integer handle) {
        this.handle = handle;
    }

    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getPs_sndmod() {
        return ps_sndmod;
    }

    public void setPs_sndmod(Integer ps_sndmod) {
        this.ps_sndmod = ps_sndmod;
    }

    public Integer getPs_sndspn() {
        return ps_sndspn;
    }

    public void setPs_sndspn(Integer ps_sndspn) {
        this.ps_sndspn = ps_sndspn;
    }

    public Integer getPs_rtcall() {
        return ps_rtcall;
    }

    public void setPs_rtcall(Integer ps_rtcall) {
        this.ps_rtcall = ps_rtcall;
    }

    public List<AttrDTO> get_attrs() {
        return _attrs;
    }

    public void set_attrs(List<AttrDTO> _attrs) {
        this._attrs = _attrs;
    }

    public List<SensorDTO> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorDTO> sensors) {
        this.sensors = sensors;
    }

    @Override
    public String toString() {
        return "DeviceDTO{" +
                "rsn=" + rsn +
                ", sn='" + sn + '\'' +
                ", handle=" + handle +
                ", ver=" + ver +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", ps_sndmod=" + ps_sndmod +
                ", ps_sndspn=" + ps_sndspn +
                ", ps_rtcall=" + ps_rtcall +
                ", _attrs=" + _attrs +
                ", sensors=" + sensors +
                '}';
    }
}
