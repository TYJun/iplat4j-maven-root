(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-66be5b76"],{"1bef":function(e,t,r){"use strict";r("399b")},"399b":function(e,t,r){},e3ce:function(e,t,r){"use strict";r.r(t),r("4662");var a=r("28a2"),s=r("1da1"),u=(r("96cf"),r("d81d"),r("b0c0"),r("a434"),r("b64b"),r("365c")),n={name:"data-upload-center",props:{otherStateUserInfo:{type:Object,default:function(){return{}}}},render:function(){var a=this,t=arguments[0];return t("div",{class:"data-upload-center"},[this.upload_list.map(function(r,e){return t("div",{class:"data-upload-center-container"},[t("div",{class:"data-upload-center-sign-image"},[t("global-van-uploader",{attrs:{"file-list":a.file_list[r.key],multiple:!0,"max-count":3,"max-size":8388608},on:{delete:function(e,t){return a.uploader_uploader_delete(r.key,e.file_id)},oversize:function(){return a.uploader_oversize()}},scopedSlots:{default:function(e){return t("span")},previewCover:function(e){return t("div",{class:"data-upload-center-sign-image-item-uploader",on:{click:function(){return a.uploading_image_preview(r.key)}}},[t("img",{attrs:{width:"80",height:"80",src:e.url}})])}}})]),t("global-van-field",{attrs:{center:!0,clearable:!0,required:r.required,label:r.text,placeholder:""},class:"data-upload-center-button-field"},[t("template",{slot:"button"},[t("global-tap",{on:{tap:function(){return a.uploader_photograph_local_tap(r.key,"CAMERA",r.resources)}}},[t("global-van-button",{attrs:{icon:"photograph",type:"primary"}},["相机拍摄"])]),t("global-tap",{class:"complete-button-loader",on:{tap:function(){return a.uploader_photograph_local_tap(r.key,"PHOTOLIBRARY",r.resources)}}},[t("global-van-button",{attrs:{icon:"photo-fail",type:"primary"}},["本地上传"])])])])])})])},data:function(){return{projectId:this.$route.query.id,upload_list:[{text:"签订协议",key:"sign",resources:"01",required:!0},{key:"risk",text:"风险评估",resources:"02",required:!0},{key:"security",text:"安全措施",resources:"03",required:!0},{key:"other",text:"其他",resources:"04",required:!1}],file_list:{sign:[],risk:[],security:[],other:[]},files:{sign:[],risk:[],security:[],other:[]}}},computed:{},watch:{projectId:{handler:function(e,t){console.dir(this.projectId)},deep:!0,immediate:!0}},methods:{uploader_after:function(r,a,n,i){var o=this;return Object(s.a)(regeneratorRuntime.mark(function e(){var t;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return o.$toast.loading({message:"上传中...",duration:0,forbidClick:!0}),o.files[a].push(n),e.next=4,Object(u.i)({workNo:o.workNo,name:o.name,relateId:"",statusCode:"01",fileResources:i,base64:n,fileName:"".concat((new Date).getTime(),".png")});case 4:t=e.sent,o.$toast.clear(),t.success?(o.file_list[a].push({url:r,base64:n,file_id:t.data}),o.emit_file_ids()):o.$notify({type:"danger",message:"上传失败"});case 7:case"end":return e.stop()}},e)}))()},uploader_uploader_delete:function(r,a){var n=this;this.file_list[r].map(function(e,t){return e.file_id==a&&n.file_list[r].splice(t,1)}),this.emit_file_ids()},emit_file_ids:function(){var t=this,r=[];Object.keys(this.file_list).map(function(e){return t.file_list[e].map(function(e){return r.push(e.file_id)})}),this.$emit("uploader",r),this.$emit("buttonshow",this.file_list)},uploader_photograph_local_tap:function(a,r,n){var i=this;return Object(s.a)(regeneratorRuntime.mark(function e(){var t;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:t={PHOTOLIBRARY:{destinationType:Camera.DestinationType.FILE_URI,sourceType:Camera.PictureSourceType.PHOTOLIBRARY,allowEdit:!1,mediaType:Camera.MediaType.PICTURE},CAMERA:{quality:50,destinationType:Camera.DestinationType.FILE_URI,sourceType:Camera.PictureSourceType.CAMERA,allowEdit:!1,encodingType:Camera.EncodingType.JPEG,popoverOptions:CameraPopoverOptions,saveToPhotoAlbum:!1}},i.m_native_camera_get_picture(t[r],function(){var t=Object(s.a)(regeneratorRuntime.mark(function e(t){var r;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:if(r=[function(){return i.$notify({type:"danger",message:"您未拍照或者未选择照片"})},function(){var t=Object(s.a)(regeneratorRuntime.mark(function e(t){var r;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,i.$utils.getImgToBase64(t);case 2:r=e.sent,i.uploader_after(t,a,r,n);case 4:case"end":return e.stop()}},e)}));return function(e){return t.apply(this,arguments)}}()],e.t0=t.success,e.t0)return e.next=5,r[t.data.status](t.data.url);e.next=5;break;case 5:case"end":return e.stop()}},e)}));return function(e){return t.apply(this,arguments)}}());case 2:case"end":return e.stop()}},e)}))()},uploading_image_preview:function(e){Object(a.a)(this.file_list[e].map(function(e){return e.url}))},uploader_oversize:function(){this.$notify({type:"danger",message:"图片大小不能超过2M"})}}},r=(r("1bef"),r("2877")),n=Object(r.a)(n,void 0,void 0,!1,null,"73fd1921",null);t.default=n.exports}}]);