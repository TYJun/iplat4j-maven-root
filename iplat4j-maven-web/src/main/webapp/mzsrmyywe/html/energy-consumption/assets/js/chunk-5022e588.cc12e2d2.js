(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-5022e588"],{"5d16":function(e,a,s){"use strict";s.r(a);var t=s("1da1"),i=(s("4662"),s("28a2")),r=(s("96cf"),s("d81d"),s("365c")),n={name:"branched-passage-boss-details-preview",props:{},render:function(){var s=this,t=arguments[0];return t("div",{class:"branched-passage-boss-details-preview"},[t("div",{class:"branched-passage-boss-details-preview-container"},[t("div",{class:"branched-passage-boss-details-preview-title"},["签订协议"]),t("div",{class:"branched-passage-boss-details-preview-img"},[this.file_sign.map(function(e,a){return t("global-tap",{class:"branched-passage-boss-details-preview-box",attrs:{width:"80",height:"80"},on:{tap:function(){s.uploading_image_preview(e)}}},[t("global-van-image",{class:"global-van-image-preview",attrs:{src:e,width:"5rem",height:"5rem"}})])})])]),t("div",{class:"branched-passage-boss-details-preview-container"},[t("div",{class:"branched-passage-boss-details-preview-title"},["风险评估"]),t("div",{class:"branched-passage-boss-details-preview-img"},[this.file_risk.map(function(e,a){return t("global-tap",{class:"branched-passage-boss-details-preview-box",attrs:{width:"80",height:"80"},on:{tap:function(){s.uploading_image_preview(e)}}},[t("global-van-image",{class:"global-van-image-preview",attrs:{src:e,width:"5rem",height:"5rem"}})])})])]),t("div",{class:"branched-passage-boss-details-preview-container"},[t("div",{class:"branched-passage-boss-details-preview-title"},["安全措施"]),t("div",{class:"branched-passage-boss-details-preview-img"},[this.file_safe.map(function(e,a){return t("global-tap",{class:"branched-passage-boss-details-preview-box",attrs:{width:"80",height:"80"},on:{tap:function(){s.uploading_image_preview(e)}}},[t("global-van-image",{class:"global-van-image-preview",attrs:{src:e,width:"5rem",height:"5rem"}})])})])]),t("div",{class:"branched-passage-boss-details-preview-container"},[t("div",{class:"branched-passage-boss-details-preview-title"},["其他"]),t("div",{class:"branched-passage-boss-details-preview-img"},[this.file_other.map(function(e,a){return t("global-tap",{class:"branched-passage-boss-details-preview-box",attrs:{width:"80",height:"80"},on:{tap:function(){s.uploading_image_preview(e)}}},[t("global-van-image",{class:"global-van-image-preview",attrs:{src:e,width:"5rem",height:"5rem"}})])})])])])},data:function(){return{projectId:this.$route.query,file_sign:[],file_risk:[],file_safe:[],file_other:[]}},computed:{condition_computed:function(){return{relateId:""}}},watch:{condition_computed:{handler:function(){this.uploader_file_sign(),this.uploader_file_risk(),this.uploader_file_safe(),this.uploader_file_other()},deep:!0,immediate:!0}},methods:{group_tap:function(){this.$rount.push({path:"/"})},uploading_image_preview:function(e){Object(i.a)([e])},uploader_file_sign:function(){var s=this;return Object(t.a)(regeneratorRuntime.mark(function e(){var a;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(r.k)({relateId:s.projectId.id,statusCode:"01",fileResources:"01"});case 2:a=e.sent,s.file_sign.push(a.data[0].fileUrl),s.file_sign.push(a.data[1].fileUrl),s.file_sign.push(a.data[2].fileUrl);case 6:case"end":return e.stop()}},e)}))()},uploader_file_risk:function(){var s=this;return Object(t.a)(regeneratorRuntime.mark(function e(){var a;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(r.k)({relateId:s.projectId.id,statusCode:"01",fileResources:"02"});case 2:a=e.sent,s.file_risk.push(a.data[0].fileUrl),s.file_risk.push(a.data[1].fileUrl),s.file_risk.push(a.data[2].fileUrl);case 4:case"end":return e.stop()}},e)}))()},uploader_file_safe:function(){var s=this;return Object(t.a)(regeneratorRuntime.mark(function e(){var a;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(r.k)({relateId:s.projectId.id,statusCode:"01",fileResources:"03"});case 2:a=e.sent,console.log(a),s.file_safe.push(a.data[0].fileUrl),s.file_safe.push(a.data[1].fileUrl),s.file_safe.push(a.data[2].fileUrl);case 5:case"end":return e.stop()}},e)}))()},uploader_file_other:function(){var s=this;return Object(t.a)(regeneratorRuntime.mark(function e(){var a;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(r.k)({relateId:s.projectId.id,statusCode:"01",fileResources:"04"});case 2:a=e.sent,s.file_other.push(a.data[0].fileUrl),s.file_other.push(a.data[1].fileUrl),s.file_other.push(a.data[2].fileUrl);case 4:case"end":return e.stop()}},e)}))()}}},s=(s("ffa4"),s("2877")),n=Object(s.a)(n,void 0,void 0,!1,null,"2eed8626",null);a.default=n.exports},9773:function(e,a,s){},ffa4:function(e,a,s){"use strict";s("9773")}}]);