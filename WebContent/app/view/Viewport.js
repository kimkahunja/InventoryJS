Ext.define('kahunja.view.Viewport', {
    extend: 'Ext.container.Viewport',
    requires:[
        'Ext.layout.container.Border',
        'kahunja.view.layout.West',
        'kahunja.view.layout.Center'
    ],

    layout: {
        type: 'border'
    },

    items: [{
        xtype: 'layout.west'       
    },
    {xtype: 'layout.center'}
    ]
});
