function $ajax(methodName, serviceName, data, call_back) {
    // console.log(methodName, serviceName);
    $.ajax({
        url: baseUrl + "MobileAgentService",
        type: "post",
        headers: {
            methodName: methodName,
            serviceName: serviceName,
        },
        data: { prames: data },
        success: function load(data) {
            call_back(data);
        },
    });
}