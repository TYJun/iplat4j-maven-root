(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-39033e1e"],{"1f65":function(e,a,t){"use strict";t.r(a);var s=t("1da1"),i=(t("4662"),t("28a2")),r=(t("96cf"),t("d81d"),t("365c")),n={name:"project-details-list",props:{},render:function(){var t=this,s=arguments[0];return s("div",{class:"project-details-list"},[s("global-tap",{attrs:{width:"0%"},class:"project-details-list-global-tap"},[s("global-van-cell-group",{attrs:{inset:!0}},[s("div",{class:"branched-passage-boss-details-preview"},[s("div",{class:"branched-passage-boss-details-preview-container"},[s("div",{class:"branched-passage-boss-details-preview-title"},["签订协议"]),s("div",{class:"branched-passage-boss-details-preview-img"},[this.file_sign.map(function(e,a){return s("global-tap",{class:"branched-passage-boss-details-preview-box",attrs:{width:"80",height:"80"},style:"padding-left: 6%",on:{tap:function(){t.uploading_image_preview(e)}}},[s("global-van-image",{class:"global-van-image-preview",attrs:{src:e,width:"5rem",height:"5rem"}})])})])]),s("div",{class:"branched-passage-boss-details-preview-container"},[s("div",{class:"branched-passage-boss-details-preview-title"},["风险评估"]),s("div",{class:"branched-passage-boss-details-preview-img"},[this.file_risk.map(function(e,a){return s("global-tap",{class:"branched-passage-boss-details-preview-box",attrs:{width:"80",height:"80"},style:"padding-left: 6%",on:{tap:function(){t.uploading_image_preview(e)}}},[s("global-van-image",{class:"global-van-image-preview",attrs:{src:e,width:"5rem",height:"5rem"}})])})])]),s("div",{class:"branched-passage-boss-details-preview-container"},[s("div",{class:"branched-passage-boss-details-preview-title"},["安全措施"]),s("div",{class:"branched-passage-boss-details-preview-img"},[this.file_safe.map(function(e,a){return s("global-tap",{class:"branched-passage-boss-details-preview-box",attrs:{width:"80",height:"80"},style:"padding-left: 6%",on:{tap:function(){t.uploading_image_preview(e)}}},[s("global-van-image",{class:"global-van-image-preview",attrs:{src:e,width:"5rem",height:"5rem"}})])})])]),s("div",{class:"branched-passage-boss-details-preview-container"},[s("div",{class:"branched-passage-boss-details-preview-title"},["其他"]),s("div",{class:"branched-passage-boss-details-preview-img"},[this.file_other.map(function(e,a){return s("global-tap",{class:"branched-passage-boss-details-preview-box",attrs:{width:"80",height:"80"},style:"padding-left: 6%",on:{tap:function(){t.uploading_image_preview(e)}}},[s("global-van-image",{class:"global-van-image-preview",attrs:{src:e,width:"5rem",height:"5rem"}})])})])])])])])])},data:function(){return{projectId:this.$route.query,file_sign:[],file_risk:[],file_safe:[],file_other:[]}},computed:{condition_computed:function(){return{relateId:""}}},watch:{condition_computed:{handler:function(){this.uploader_file_sign(),this.uploader_file_risk(),this.uploader_file_safe(),this.uploader_file_other()},deep:!0,immediate:!0}},methods:{group_tap:function(){this.$rount.push({path:"/"})},uploading_image_preview:function(e){Object(i.a)([e])},uploader_file_sign:function(){var t=this;return Object(s.a)(regeneratorRuntime.mark(function e(){var a;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(r.k)({relateId:t.projectId.id,statusCode:"01",fileResources:"01"});case 2:a=e.sent,t.file_sign.push(a.data[0].fileUrl),t.file_sign.push(a.data[1].fileUrl),t.file_sign.push(a.data[2].fileUrl);case 6:case"end":return e.stop()}},e)}))()},uploader_file_risk:function(){var t=this;return Object(s.a)(regeneratorRuntime.mark(function e(){var a;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(r.k)({relateId:t.projectId.id,statusCode:"01",fileResources:"02"});case 2:a=e.sent,t.file_risk.push(a.data[0].fileUrl),t.file_risk.push(a.data[1].fileUrl),t.file_risk.push(a.data[2].fileUrl);case 4:case"end":return e.stop()}},e)}))()},uploader_file_safe:function(){var t=this;return Object(s.a)(regeneratorRuntime.mark(function e(){var a;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(r.k)({relateId:t.projectId.id,statusCode:"01",fileResources:"03"});case 2:a=e.sent,console.log(a),t.file_safe.push(a.data[0].fileUrl),t.file_safe.push(a.data[1].fileUrl),t.file_safe.push(a.data[2].fileUrl);case 5:case"end":return e.stop()}},e)}))()},uploader_file_other:function(){var t=this;return Object(s.a)(regeneratorRuntime.mark(function e(){var a;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(r.k)({relateId:t.projectId.id,statusCode:"01",fileResources:"04"});case 2:a=e.sent,t.file_other.push(a.data[0].fileUrl),t.file_other.push(a.data[1].fileUrl),t.file_other.push(a.data[2].fileUrl);case 4:case"end":return e.stop()}},e)}))()}}},t=(t("fe49"),t("2877")),n=Object(t.a)(n,void 0,void 0,!1,null,"bacfa864",null);a.default=n.exports},cf30:function(e,a,t){},fe49:function(e,a,t){"use strict";t("cf30")}}]);