var Ani = Class.create();

Ani.prototype = {
    time   : 600,
    sepTime: 20,
    autoStart: true,

    //回调函数
    onstart  : function(){},
    onevery  : function(){},
    onpause  : function(){},
    onstop   : function(){},

    initialize: function(el,opt){
        this._el = el;
        Object.extend(this,opt);
        if(this.autoStart)this.start();
    },

    start : function(){
        if(this.running){return;}

        this.running = true;
        //进度计数
        this._kicks = parseInt( this.time / this.sepTime ) + 1;

        this.onstart.call(this, this._el);
        this._timer = setInterval(this.every.bind(this), this.sepTime);
        this._startTime = +( new Date() );
    },
    
    every : function(){
        var now     = +( new Date() );
        this.percent = ( now - this._startTime )/this.time;
        if( this.percent > 0.98 ){
            this.stop();
        }else{
            this.onevery.call(this, this._el, this.percent);
        }
    },

    pause : function(){
        this.remainTime = -(new Date()) + this._startTime + this.time;
        if(this.remainTime < 0){
            this.remainTime = 0;
            this.percent    = 1;
            this.stop();
        }else{
            clearInterval(this._timer);
        }
    },

    stop  : function(){
        clearInterval(this._timer);
        this.onstop.call(this, this._el);
        this.running = false;
    },

    /**
     *  转换进度值为数值, 模拟easein easeout效果
     */
    _valueFn: function(percent){
        /** 这里使用三次函数f(x)=(3-2x)*x^2, 作为增长的转换函数
         *  特点 是f'(x)连续，变化连续，先加快，后减速
            f(0) = f'(0) = 0, f(1) = f'(1) =1, f(0.5) = 0.5
         */
        return percent * percent * ( 3 - 2 * percent );
    }
}
