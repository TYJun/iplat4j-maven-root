(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-1bbcaae2"],{"887f":function(t,e,i){i("a29f"),i("f6d2")},bbbb:function(t,e,i){"use strict";i.r(e),i("68ef"),i("bd4f"),i("a9e3"),i("d81d"),i("2c3e"),i("d3b7"),i("159b"),i("4de4");var n=i("d282"),c=i("a142"),h=i("02de"),o=i("1325"),l=i("a8c1"),r=i("3875"),s=i("9884"),a=i("5fbe");var u=Object(n.a)("index-bar"),n=u[0],d=u[1],f=n({mixins:[r.a,Object(s.b)("vanIndexBar"),Object(a.a)(function(t){this.scroller||(this.scroller=Object(l.d)(this.$el)),t(this.scroller,"scroll",this.onScroll)})],props:{zIndex:[Number,String],highlightColor:String,sticky:{type:Boolean,default:!0},stickyOffsetTop:{type:Number,default:0},indexList:{type:Array,default:function(){for(var t=[],e="A".charCodeAt(0),i=0;i<26;i++)t.push(String.fromCharCode(e+i));return t}}},data:function(){return{activeAnchorIndex:null}},computed:{sidebarStyle:function(){if(Object(c.c)(this.zIndex))return{zIndex:this.zIndex+1}},highlightStyle:function(){var t=this.highlightColor;if(t)return{color:t}}},watch:{indexList:function(){this.$nextTick(this.onScroll)},activeAnchorIndex:function(t){t&&this.$emit("change",t)}},methods:{onScroll:function(){var n,c,o,r,s=this;Object(h.a)(this.$el)||(n=Object(l.c)(this.scroller),c=this.getScrollerRect(),o=this.children.map(function(t){return t.getRect(s.scroller,c)}),r=this.getActiveAnchorIndex(n,o),this.activeAnchorIndex=this.indexList[r],this.sticky&&this.children.forEach(function(t,e){var i;e===r||e===r-1?(i=t.$el.getBoundingClientRect(),t.left=i.left,t.width=i.width):(t.left=null,t.width=null),e===r?(t.active=!0,t.top=Math.max(s.stickyOffsetTop,o[e].top-n)+c.top):e===r-1?(i=o[r].top-n,t.active=0<i,t.top=i+c.top-o[e].height):t.active=!1}))},getScrollerRect:function(){return this.scroller.getBoundingClientRect?this.scroller.getBoundingClientRect():{top:0,left:0}},getActiveAnchorIndex:function(t,e){for(var i=this.children.length-1;0<=i;i--){var n=0<i?e[i-1].height:0;if(t+(this.sticky?n+this.stickyOffsetTop:0)>=e[i].top)return i}return-1},onClick:function(t){this.scrollToElement(t.target)},onTouchMove:function(t){var e;this.touchMove(t),"vertical"===this.direction&&(Object(o.c)(t),t=(e=t.touches[0]).clientX,e=e.clientY,(t=document.elementFromPoint(t,e))&&(e=t.dataset.index,this.touchActiveIndex!==e&&(this.touchActiveIndex=e,this.scrollToElement(t))))},scrollTo:function(e){var t=this.children.filter(function(t){return String(t.index)===e});t[0]&&(t[0].scrollIntoView(),this.sticky&&this.stickyOffsetTop&&Object(l.g)(Object(l.b)()-this.stickyOffsetTop),this.$emit("select",t[0].index))},scrollToElement:function(t){t=t.dataset.index;this.scrollTo(t)},onTouchEnd:function(){this.active=null}},render:function(){var i=this,n=arguments[0],t=this.indexList.map(function(t){var e=t===i.activeAnchorIndex;return n("span",{class:d("index",{active:e}),style:e?i.highlightStyle:null,attrs:{"data-index":t}},[t])});return n("div",{class:d()},[n("div",{class:d("sidebar"),style:this.sidebarStyle,on:{click:this.onClick,touchstart:this.touchStart,touchmove:this.onTouchMove,touchend:this.onTouchEnd,touchcancel:this.onTouchEnd}},[t]),this.slots("default")])}}),a=i("2638"),b=i.n(a),v=i("5530"),a=(i("887f"),{name:"global-van-index-bar",props:{},render:function(){return(0,arguments[0])(f,b()([{},{attrs:Object(v.a)({},this.attrs)},{},{on:this.$listeners}]),[this.$slots.default])},inheritAttrs:!1,computed:{attrs:function(){return Object(v.a)(Object(v.a)({},this.$attrs),this.$props)}},data:function(){return{}}}),i=i("2877"),a=Object(i.a)(a,void 0,void 0,!1,null,"7f689b58",null);e.default=a.exports},bd4f:function(t,e,i){},f6d2:function(t,e,i){}}]);