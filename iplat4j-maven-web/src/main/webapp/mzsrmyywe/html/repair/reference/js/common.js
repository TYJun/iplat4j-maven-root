function AbstractEi() {
  this.extAttr = {};
}
AbstractEi.prototype.get = function(prop) {
  return this.extAttr[prop];
};
AbstractEi.prototype.set = function(prop, value) {
  this.extAttr[prop] = value;
};
EiInfo.prototype = new AbstractEi();
EiInfo.prototype.constructor = EiInfo;
EiInfo.prototype.set = function() {};
