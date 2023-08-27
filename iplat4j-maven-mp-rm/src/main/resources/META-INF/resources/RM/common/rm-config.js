/**物资领用配置集合**/
let rmConfigs = {};

/**
 * 查询所有配置
 */
function queryConfig() {
    EiCommunicator.send("RMPZ01", "getAllConfig", new EiInfo(), {
        onSuccess: function (ei) {
            if (ei.getStatus() == -1) {
                NotificationUtil(ei.getMsg(), "error");
                return;
            }
            rmConfigs = ei.get("configs")
        }
    }, {async: false});
}

/**
 * 获取指定配置
 * @param configCode: 配置编码
 */
function getConfig(configCode) {
    //配置不存在,查询配置
    if(!rmConfigs.length || rmConfigs.length < 1) { queryConfig(); }
    return rmConfigs[configCode]
}

/**
 * 判断指定配置是否生效
 * @param configCode: 配置编码
 */
function isEffect(configCode) {
    let config = getConfig(configCode);
    return "Y" == config['configValueRadio'] ? true : false;
}

/**
 * 获取指定配置的输入库框值
 * @param configCode
 */
function getTextValue(configCode) {
    let config = getConfig(configCode);
    return config['configValueText'];
}

