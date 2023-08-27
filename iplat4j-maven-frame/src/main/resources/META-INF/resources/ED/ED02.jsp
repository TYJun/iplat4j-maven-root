<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    /*.c-layout-detail{*/
    /*    display: flex;*/
    /*    justify-content: space-around;*/
    /*    align-items: baseline;*/
    /*    align-content: baseline;*/
    /*}*/

    /* 拾色板 */

    .drawer-container {
        width: 100%;
        height: calc(100vh - 77px);
        box-sizing: border-box;
        padding: 0 20px 20px 20px;
        overflow: auto;
    }

    .drawer-group-container {
        width: 100%;
        box-sizing: border-box;
    }

    .drawer-group-title {
        width: 100%;
        display: flex;
    }

    .drawer-group-name {
        font-size: 20px;
        font-weight: bold;
        color: #333;
    }

    .drawer-group-item {
        width: 100%;
        box-sizing: border-box;
        margin-top: 20px;
        padding-bottom: 20px;
    }

    .drawer-group-item-title {
        width: 100%;
        display: flex;
        align-items: flex-end;
    }

    .drawer-group-item-name {
        font-size: 16px;
        color: #333;
    }

    .drawer-group-item-desc {
        font-size: 14px;
        color: rgb(155,155,155);
        margin-left: 15px;
    }

    .drawer-color-container {
        width: 100%;
        box-sizing: border-box;
        display: flex;
        justify-content: flex-start;
        align-items: flex-end;
        margin-top: 10px;
        height: calc(80vw / 17.5);
    }

    .drawer-color-item {
        height: calc(80vw / 19);
        display: flex;
        flex-flow: column nowrap;
        justify-content: center;
        align-items: center;
        cursor: pointer;
    }

    .drawer-color-item:hover {
        box-shadow: 0 0 10px 1px rgba(0,0,0,.2);
        z-index: 9;
        height: calc(80vw / 17.5);
        border-top-left-radius: 3px;
        border-top-right-radius: 3px;
    }

    .drawer-color-item-base {
        height: calc(80vw / 17.5);
        border-top-left-radius: 3px;
        border-top-right-radius: 3px;
    }

    .drawer-color-item-name {
        overflow: hidden;
        font-size: 12px;
        font-weight: bold;
    }

    .drawer-color-item-hex {
        overflow: hidden;
        margin-top: 12px;
        font-size: 10px;
        transform: scale(0.9);
    }

    .drawer-color-item-rgb {
        overflow: hidden;
        font-size: 8px;
        transform: scale(0.9);
    }

    .drawer-color-item .text-light-color {
        color: #f0f0f0;
    }

    /* 拾色板 */
</style>
<script id="colorTemplate" type="text/x-kendo-template">
    # for (var i = 0; i < COLORS.length; i++) { #
        # var group = COLORS[i] #
        # console.log(group) #
        # if(!group) {continue} #
        # var child = group.child #
        <div class="drawer-group-container">
            <div class="drawer-group-title">
                <div class="drawer-group-name">#= group.groupName#</div>
            </div>
            # for (var j = 0; j < child.length; j++) { #
                # var colors = child[j].colors #
                <div class="drawer-group-item">
                    <div class="drawer-group-item-title">
                        <div class="drawer-group-item-name">#= child[j].itemName#</div>
                        <div class="drawer-group-item-desc">#= child[j].itemDesc#</div>
                    </div>
                    <div class="drawer-color-container">
                        # for (var k = 0; k < colors.length; k++) { #
                            # var color = colors[k] #
                            # var hex = colors[k].hex #
                            # var length = colors.length #
                            # var style = 'background:' + hex +';width: calc(98%/' + length + ')'#
                            # var text_light = length/2-1<k #
                            # var clazzName = text_light ? "drawer-color-item-name text-light-color":"drawer-color-item-name" #
                            # var clazzHex = text_light ? "drawer-color-item-hex text-light-color":"drawer-color-item-hex" #
                            # var clazzRgb = text_light ? "drawer-color-item-rgb text-light-color":"drawer-color-item-rgb" #
                            <div class="drawer-color-item drawer-color-item-base kendotooltip"
                                 style="#= style#" title="#= color.hex#" onclick="setColor('#= color.colorName#','#= color.hex#')">
                                # if(hex.length<25) {#
                                    <div class="#=clazzName#">
                                        #=color.colorName#</div>
                                    <div class="#=clazzHex#">
                                        #=color.hex#</div>
                                    <div class="#=clazzRgb#">
                                        #=hexToRgb(color.hex)#</div>
                                # } #
                            </div>
                        # } #
                    </div>
                </div>
            # } #
        </div>
    # } #
</script>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage>
    <div class="col-lg-3">
        <EF:EFRegion id="left" title="选择区" fitHeight="true">
            <ul id="panelbar">
                <li id="layout">布局类
                    <ul>
                        <li id="layout-efpage">EFPage
                            <ul>
                                <li data-dirty="false" data-color="linear-gradient(to right,#2b76DB,#599ff6)" data-class=".i-theme-ant .i-page-header" data-property="background">标题栏背景色

                                </li>
                                <li data-dirty="false" data-color="#FFFFFF" data-class=".i-theme-ant .i-page-header .i-text" data-property="color">标题栏字体色

                                </li>

                                <li data-dirty="false" data-color="#F7FAFF" data-class="html.i-theme-ant #main-container,html.i-theme-ant #page-container" data-property="background">页面背景色

                                </li>
                            </ul>

                        </li>
                        <li id="layout-eftab">EFTab
                            <ul>
                                <li data-dirty="false" data-color="#2f80ed" data-class=".i-theme-ant .k-tabstrip.k-widget ul.k-tabstrip-items" data-property="border-bottom" data-prefix="1px solid">标签条底部边框颜色

                                </li>
                                <li data-dirty="false" data-color="#dbefff" data-class=".i-theme-ant .k-tabstrip.k-widget ul.k-tabstrip-items" data-property="background">标签条背景色

                                </li>
                                <li data-dirty="false" data-color="#dbefff" data-class=".i-theme-ant .k-tabstrip.k-widget ul.k-tabstrip-items .k-item,.i-theme-ant .k-tabstrip.k-widget ul.k-tabstrip-items .k-state-hover"  data-property="background" data-repair="#tabstrip-state-active-bgc">标签背景色

                                </li>
<%--                                <li data-dirty="false" data-color="#dbefff" data-class=".i-theme-ant .k-tabstrip.k-widget ul.k-tabstrip-items .k-item,.i-theme-ant .k-tabstrip.k-widget ul.k-tabstrip-items .k-state-hover"  data-property="background" data-repair=".i-theme-ant .k-tabstrip.k-widget ul.k-tabstrip-items .k-state-active@background@#tabstrip-state-active-bgc">标签背景色--%>

<%--                                </li>--%>
                                <li data-dirty="false" id="tabstrip-state-active-bgc" data-color="#3088f4" data-class=".i-theme-ant .k-tabstrip.k-widget ul.k-tabstrip-items .k-state-active" data-property="background">标签背景色（激活时）

                                </li>
                                <li data-dirty="false" data-color="#2f80ed" data-class=".i-theme-ant .k-tabstrip.k-widget ul.k-tabstrip-items .k-state-default .k-link"  data-property="color">标签字体色

                                </li>
                                <li data-dirty="false" data-color="#1F65ED" data-class=".i-theme-ant .k-tabstrip.k-widget ul.k-tabstrip-items .k-state-hover .k-link" data-property="color">标签字体色（悬浮时）

                                </li>
                            </ul>
                        </li>
                        <li id="layout-efregion">EFRegion
                            <ul>
                                <li data-dirty="false" data-color="#dbefff" data-class=".i-theme-ant .i-region .i-region-header" data-property="background">标题栏背景色

                                </li>
                                <li data-dirty="false" data-color="#2f80ed" data-class=".i-theme-ant .i-region .i-region-header" data-property="color">标题栏字体色

                                </li>
                            </ul>
                        </li>
                        <li id="layout-efwindow">EFWindow
                            <ul>
                                <li data-dirty="false" data-color="linear-gradient(to right,#1F65DA,#368FFF)" data-class=".i-theme-ant .k-window>.k-header" data-property="background">标题栏背景色

                                </li>
                                <li data-dirty="false" data-color="#FFFFFF" data-class=".i-theme-ant .k-window .k-window-title" data-property="color">标题栏字体色

                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li id="form">表单类
                    <ul>
                        <li >表单类通用
                            <ul>
                                <li data-dirty="false" data-color="rgba(0,0,0,.15)" data-group="formBorderColorGroup"  data-property="border-color">边框颜色

                                </li>
                                <li data-dirty="false" data-color="#2f80ed" data-group="formBorderColorActiveGroup"  data-property="border-color">边框颜色（激活时）

                                </li>
                            </ul>
                        </li>
                        <li >日期类通用
                            <ul>
                                <li data-dirty="false" data-color="#3088f4" data-group="datepickerPanelActive"  data-property="border-color,background-color">当前选中日期背景色及边框色

                                </li>
                                <li data-dirty="false" data-color="rgba(48,136,244,.8)" data-class=".i-theme-ant .k-animation-container .k-calendar-container .k-calendar .k-content .k-state-hover .k-link"  data-property="border-color,background-color">悬浮日期背景色及边框色

                                </li>
                                <li data-dirty="false" data-color="#3088f4" data-group="datepickerPanelFont"  data-property="color">日期框内字体颜色

                                </li>
                                <li data-dirty="false" id="datepicker-panel-secondary-btn-bgc" data-color="#fff" data-class=".i-theme-ant .i-btn-text"  data-property="background,border-color">清空、关闭按钮背景色

                                </li>
                                <li data-dirty="false" id="datepicker-panel-secondary-btn-bgc-hover" data-color="#F6F6F6" data-class=".i-theme-ant .i-btn-text:hover"  data-property="background,border-color">清空、关闭按钮背景色（悬浮时）

                                </li>
                                <li data-dirty="false" id="datepicker-panel-secondary-btn-bgc-active" data-color="#F6F6F6" data-class=".i-theme-ant .i-btn-text:active"  data-property="background,border-color">清空、关闭按钮背景色（激活时）

                                </li>
<%--                                <li data-dirty="false" data-color="#fff" data-class=".i-theme-ant .i-btn-text"  data-property="border-color">清空、关闭按钮边框色--%>

<%--                                </li>--%>
<%--                                <li data-dirty="false" data-color="#fff" data-class=".i-theme-ant .i-btn-text:hover"  data-property="border-color">清空、关闭按钮边框色（悬浮时）--%>

<%--                                </li>--%>
                                <li data-dirty="false" id="datepicker-panel-secondary-btn-color" data-color="#3088f4" data-group="datepickerPanelBtnText"  data-property="color">清空、关闭按钮字体色

                                </li>
<%--                                <li data-dirty="false" data-color="#3088f4" data-class=".i-theme-ant .i-btn-text:hover"  data-property="color">清空、关闭按钮字体色（悬浮时）--%>

<%--                                </li>--%>
                            </ul>
                        </li>
                        <li >下拉类通用
                            <ul>
                                <li data-dirty="false" data-color="#3088f4" data-class=".i-theme-ant .k-animation-container .k-list-scroller .k-list .k-state-selected"  data-property="background-color">下拉选项选中背景色

                                </li>
                                <li data-dirty="false" data-color="#EFF8FF" data-class=".i-theme-ant .k-animation-container .k-list-scroller .k-list>.k-state-hover"  data-property="background-color">下拉选项悬停背景色

                                </li>
                                <li data-dirty="false" data-color="#3088F4" data-class=".i-theme-ant .k-animation-container .k-list-container .k-list-optionlabel.k-state-selected"  data-property="background-color">默认空值选项选中背景色

                                </li>
                                <li data-dirty="false" data-color="#eff8ff" data-class=".i-theme-ant .k-animation-container .k-list-container .k-list-optionlabel.k-state-hover"  data-property="background-color">默认空值选项悬浮背景色

                                </li>
                            </ul>
                        </li>
                        <li >EFUpload
                        </li>
                        <li >按钮类通用
                            <ul>
                                <li data-dirty="false" data-color="#3088f4" data-group="buttonBgc"  data-property="background,border-color" data-repair="#datepicker-panel-secondary-btn-bgc">按钮背景色及边框色

                                </li>
                                <li data-dirty="false" data-color="#3088f4" data-class=".i-theme-ant .i-btn-drop:hover,.i-theme-ant .i-btn-lg-no-ripple:hover,.i-theme-ant .i-btn-lg:hover,.i-theme-ant .i-btn-ripple-bounce:hover,.i-theme-ant .i-btn-sm:hover,.i-theme-ant .i-btn:hover,.i-theme-ant .k-button:hover,.i-theme-ant .k-overflow-anchor:hover"  data-property="background,border-color" data-repair="#datepicker-panel-secondary-btn-bgc-hover">按钮背景色及边框色（悬浮时）

                                </li>
                                <li data-dirty="false" data-color="#3088f4" data-class=".i-theme-ant .i-btn-drop:active,.i-theme-ant .i-btn-lg-no-ripple:active,.i-theme-ant .i-btn-lg:active,.i-theme-ant .i-btn-ripple-bounce:active,.i-theme-ant .i-btn-sm:active,.i-theme-ant .i-btn:active,.i-theme-ant .k-button:active,.i-theme-ant .k-overflow-anchor:active"  data-property="background,border-color" data-repair="#datepicker-panel-secondary-btn-bgc-active">按钮背景色及边框色（激活时）

                                </li>
<%--                                <li data-dirty="false" data-color="#3088f4" data-class=".i-theme-ant .i-btn,.i-theme-ant .i-btn-drop,.i-theme-ant .i-btn-lg,.i-theme-ant .i-btn-lg-no-ripple,.i-theme-ant .i-btn-ripple-bounce,.i-theme-ant .i-btn-sm,.i-theme-ant .k-button,.i-theme-ant .k-overflow-anchor"  data-property="border" data-prefix="1px solid">按钮边框色--%>

<%--                                </li>--%>
<%--                                <li data-dirty="false" data-color="#3088f4" data-class=".i-theme-ant .i-btn-drop:hover,.i-theme-ant .i-btn-lg-no-ripple:hover,.i-theme-ant .i-btn-lg:hover,.i-theme-ant .i-btn-ripple-bounce:hover,.i-theme-ant .i-btn-sm:hover,.i-theme-ant .i-btn:hover,.i-theme-ant .k-button:hover,.i-theme-ant .k-overflow-anchor:hover"  data-property="border-color">按钮边框色（悬浮时）--%>

<%--                                </li>--%>
                                <li data-dirty="false" data-color="#FFF" data-class=".i-theme-ant .i-btn,.i-theme-ant .i-btn-drop,.i-theme-ant .i-btn-lg,.i-theme-ant .i-btn-lg-no-ripple,.i-theme-ant .i-btn-ripple-bounce,.i-theme-ant .i-btn-sm,.i-theme-ant .k-button,.i-theme-ant .k-overflow-anchor"  data-property="color" data-repair="#datepicker-panel-secondary-btn-color">按钮字体色

                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li id="grid">表格类
                    <ul>

                        <li data-dirty="false" data-color="#DBEFFF" data-class=".i-theme-ant .k-grid th.k-header"  data-property="background">表头背景色

                        </li>

                        <li data-dirty="false" data-color="#2f80ed" data-group="headerColor"  data-property="color">表头字体色

                        </li>

                        <li data-dirty="false" data-color="#99d2ff" data-class=".i-theme-ant .k-grid th.k-header"  data-property="border-color">表头边框色

                        </li>

                        <li data-dirty="false" data-color="#99d2ff" data-class=".i-theme-ant .k-grid td"  data-property="border-color">单元格边框颜色

                        </li>

                        <li data-dirty="false" data-color="#ffe06a" data-class=".i-theme-ant .k-grid .k-grid-content tr.k-td-hover td,.i-theme-ant .k-grid .k-grid-content tr.k-td-hover+tr td,.i-theme-ant .k-grid .k-grid-content-locked tr.k-td-hover td,.i-theme-ant .k-grid .k-grid-content-locked tr.k-td-hover+tr td,.i-theme-ant .k-grid .k-selectable tr.k-td-hover td,.i-theme-ant .k-grid .k-selectable tr.k-td-hover+tr td"  data-property="border-top-color" data-addition=".i-theme-ant .k-grid .k-grid-content tr:last-child.k-td-hover td,.i-theme-ant .k-grid .k-grid-content-locked tr:last-child.k-td-hover td,.i-theme-ant .k-grid .k-selectable tr:last-child.k-td-hover td@border-bottom-color">单元格边框颜色（悬浮时）

                        </li>

                        <li data-dirty="false" id="tr-state-selected-bgc" data-color="#ffea9c" data-group="gridSelectedRowBgc"  data-property="background">选中行背景色

                        </li>

                        <li data-dirty="false" data-color="#ffe06a" data-class=".i-theme-ant .k-grid .k-grid-content tr.i-state-selected.k-grid-edit-row,.i-theme-ant .k-grid .k-grid-content-locked tr.i-state-selected.k-grid-edit-row,.i-theme-ant .k-grid .k-selectable tr.i-state-selected.k-grid-edit-row"  data-property="background">选中行背景色（编辑时）

                        </li>

                        <li data-dirty="false" data-color="rgba(0,0,0,.65)" data-group="gridSelectedRowColor"  data-property="color" data-suffix="!important">选中行字体色

                        </li>

                        <li data-dirty="false" data-color="#eff8ff" data-class=".i-theme-ant .k-grid .k-grid-content tr.k-alt,.i-theme-ant .k-grid .k-grid-content-locked tr.k-alt,.i-theme-ant .k-grid .k-selectable tr.k-alt"  data-property="background-color" data-repair="#tr-state-selected-bgc">偶数行背景色

                        </li>

<%--                        <li data-dirty="false" data-color="#eff8ff" data-class=".i-theme-ant .k-grid .k-grid-content tr.k-alt,.i-theme-ant .k-grid .k-grid-content-locked tr.k-alt,.i-theme-ant .k-grid .k-selectable tr.k-alt"  data-property="background-color" data-repair=".i-theme-ant .k-grid .k-grid-content tr.k-state-selected,.i-theme-ant .k-grid .k-grid-content-locked tr.k-state-selected,.i-theme-ant .k-grid .k-selectable tr.k-state-selected@background@#tr-state-selected-bgc">偶数行背景色--%>

<%--                        </li>--%>

                        <li data-dirty="false" data-color="rgba(0,0,0,.65)" data-class=".i-theme-ant .k-alt"  data-property="color" data-suffix="!important">偶数行字体色

                        </li>

                        <li data-dirty="false" data-color="rgba(48,136,244,.8)" data-group="pagerButtonGroup"  data-property="border-color,background">翻页按钮背景色及边框色（悬浮时）

                        </li>

                        <li data-dirty="false" data-color="rgba(48,136,244,.8)" data-class=".i-theme-ant .k-grid .k-pager-wrap.k-grid-pager a.i-grid-count.count-active,.i-theme-ant .k-grid .k-pager-wrap.k-grid-pager a.i-grid-count.count-active:not(.k-state-disabled):hover"  data-property="border-color,background">翻页按钮背景色及边框色（激活时）

                        </li>

                        <li data-dirty="false" data-color="#2F80ED" data-group="pagerButtonGroup"  data-property="color">翻页按钮字体色（悬浮时）

                        </li>

                        <li data-dirty="false" data-color="#2F80ED" data-class=".i-theme-ant .k-grid .k-pager-wrap.k-grid-pager a.i-grid-count.count-active,.i-theme-ant .k-grid .k-pager-wrap.k-grid-pager a.i-grid-count.count-active:not(.k-state-disabled):hover"  data-property="color">翻页按钮字体色（激活时）

                        </li>
                    </ul>
                </li>
                <li id="tree">树或菜单
                    <ul>
                        <li data-dirty="false" data-color="#fff0b6" data-group="treeNodeActive"  data-property="background">树节点背景色（激活时）

                        </li>

                        <li data-dirty="false" data-color="#fff0b6" data-group="treeNodeActive"  data-property="color">树节点字体色（激活时）

                        </li>
                    </ul>
                </li>
                <li id="common">基础类
                    <ul>
                        <li data-dirty="false" data-color="#3088f4" data-class=".i-theme-ant .k-widget.k-tooltip.k-popup.k-state-border-up"  data-property="background,border-color" data-addition=".i-theme-ant .k-widget.k-tooltip.k-popup.k-state-border-up .k-callout-n@border-bottom-color">工具提示框(tooltip)背景及边框色

                        </li>
                    </ul>
                </li>
            </ul>
        </EF:EFRegion>
    </div>
    <div class="col-lg-9">
        <EF:EFRegion id="right" title="效果区" fitHeight="true">
            <div class="c-layout">
                <div>
                    <h4>布局类</h4>
                </div>
                <div class="c-layout-detail">
                    <EF:EFRegion title="EFRegion"/>
                    <EF:EFTab id="c-layout-tab" title="EFTab">
                        <div title="tab1">
                            tab1
                        </div>
                        <div title="tab2">
                            tab2
                        </div>
                        <div title="tab3">
                            tab3
                        </div>
                    </EF:EFTab>
                </div>

            </div>
            <div class="c-form">
                <h4>表单类</h4>
                <div class="c-layout-detail row">
                    <EF:EFInput ename="efinput_demo" cname="输入框" placeholder="请输入"/>
                    <EF:EFPopupInput ename="efpopupinput_demo" cname="下拉选择框"
                                     containerId="ef_popup_select"
                                     popupTitle="下拉选择框">
                    </EF:EFPopupInput>
                    <EF:EFTreeInput ename="eftreeinput_demo" cname="弹出树输入框"
                                    serviceName="EEDM06" methodName="query"
                                    valueField="node_id" textField="node_text" hasChildren="node_type"
                                    root="{node_id: 'root',node_text: '地区分布',node_type: 2,icon: 'folder'}">
                    </EF:EFTreeInput>
                </div>
                <div class="c-layout-detail row">
                    <EF:EFDatePicker ename="efdatepicker_demo" cname="时间输入框">
                    </EF:EFDatePicker>
                    <EF:EFDateSpan startName="efdatespan_demo" endName="efdatespan_demo2" startCname="开始日期" endCname="结束日期"
                                   role="datetime" interval="15" startRatio="4:8"
                                   startValue="2017-09-20 12:30" parseFormats="['yyyy-MM-ddHH:mm']"/>
                </div>
                <div class="c-layout-detail row">
                    <EF:EFSelect cname="下拉框" template="#=valueField#-#=textField#"
                                 optionLabel="请选择"
                                 ename="inqu_status-0-product_name" defaultValue="p1">
                        <EF:EFOption label="全部" value="all"/>
                        <EF:EFOption label="产品1" value="p1"/>
                        <EF:EFOption label="产品2" value="p2"/>
                        <EF:EFOption label="产品3" value="p3"/>
                        <EF:EFOption label="产品4" value="p4"/>
                    </EF:EFSelect>
                </div>
                <div class="c-layout-detail row">
                    <EF:EFUpload ename="file1" docTag="hk_file11" path="A"/>
                </div>
            </div>
            <div class="c-grid">
                <h4>表格类</h4>
                <EF:EFGrid blockId="result" autoDraw="no" autoBind="false" needAuth="false" sort="setted">

                    <EF:EFColumn ename="c1" cname="列1" sort="true"/>
                    <EF:EFComboColumn ename="c2" cname="列2">
                        <EF:EFOption value="1" label="1"/>
                        <EF:EFOption value="2" label="2"/>
                        <EF:EFOption value="3" label="3"/>
                    </EF:EFComboColumn>
<%--                    <EF:EFColumn ename="c2" cname="列2"/>--%>
                    <EF:EFColumn ename="c3" cname="列3"/>
                    <EF:EFColumn ename="c4" cname="列4" editType="datetime"/>
                </EF:EFGrid>
            </div>
            <div class="c-tree-menu">
                <h4>树或菜单类</h4>
                <EF:EFTree bindId="tree01" ename="tree_name" textField="text"
                           valueField="label" hasChildren="leaf"
                           serviceName="EDDBTT" methodName="query">
                </EF:EFTree>
            </div>
            <div class="c-common">
                <h4>基础类</h4>
                <EF:EFButton ename="notification1" cname="通知（信息）"/>
                <EF:EFButton ename="notification2" cname="通知（警告）"/>
                <EF:EFButton ename="notification3" cname="通知（错误）"/>
                <EF:EFButton ename="notification4" cname="通知（成功）"/>
                <EF:EFButton ename="prompt1" cname="询问"/>
                <EF:EFButton ename="alert1" cname="警示"/>
                <EF:EFButton ename="disable1" cname="禁用" disabled="disabled"/>
            </div>
        </EF:EFRegion>
        <EF:EFWindow id="tw" title="EFWindow" modal="false" top="90%" left="80%" height="0px" width="200px" visible="true"/>
<%--        <EF:EFWindow id="colorpicker" title="标准色盘" modal="false" height="90%" reasizable="false">--%>
<%--        </EF:EFWindow>--%>
    </div>
</EF:EFPage>
<%--<div id="flatpicker" style="display: none;position: absolute; top: 42px; left: 25%; background-color: white;z-index: 99999"></div>--%>
<div id="colorpicker"></div>
<span id="notification"></span>

