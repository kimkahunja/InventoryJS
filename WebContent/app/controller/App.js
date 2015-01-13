/**
 * Main controller for all top-level application functionality
 */
Ext.define('kahunja.controller.App', {
    extend: 'kahunja.controller.Base',
    views: [
        'layout.Menu',
        'layout.Center',
        'layout.Landing'
    ],
    refs: [
        {
            ref: 'Menu',
            selector: '[xtype=layout.menu]'
        },
        {
            ref: 'CenterRegion',
            selector: '[xtype=layout.center]'
        }
    ],
    init: function() {
        this.listen({
            controller: {
                '#App': {
                    tokenchange: this.dispatch
                }
            },
            component: {
                'menu[xtype=layout.menu] menuitem': {
                    click: this.addHistory
                } 
            },
            global: {},
            store: {}  
        });
    },
    /**
     * Add history token to Ext.util.History
     * @param {Ext.menu.Item} item
     * @param {Object} e
     * @param {Object} opts
     */
    addHistory: function( item, e, opts ) {
        var me = this,
            token = item.itemId;
        Ext.util.History.add( token );
        me.fireEvent( 'tokenchange', token )
    },
    /**
     * Handles token change and directs creation of content in center region
     * @param {String} token
     */
    dispatch: function( token ) {
        var me = this,
            config;
        // switch on token to determine which content to create
        switch( token ) {
            case 'staff':
                config = {
                    xtype: 'panel',
                    title: 'Staff',
                    html: 'Some staff content'
                };
                break;
            case 'option/category':
            	config = {
                    xtype: 'categories.list',
                    title: 'Manage Item Categories',
                    iconCls: 'icon_color',
                    store: Ext.create( 'kahunja.store.categories.Categories', {
                        pageSize: 30
                    })
                };
            	 break;
            case 'option/location':
            	config = {
                    xtype: 'location.locations',
                    title: 'Manage Item Locations',
                    iconCls: 'icon_color',
                    store: Ext.create( 'kahunja.store.location.Locations', {
                        pageSize: 30
                    })
                };
                break;
            case 'option/unit':
            	config = {
                    xtype: 'units.unitlist',
                    title: 'Manage Item Units',
                    iconCls: 'icon_color',
                    store: Ext.create( 'kahunja.store.units.Units', {
                        pageSize: 30
                    })
                };
            	 break;    
            case 'inventory':
                config = {
                    xtype: 'panel',
                    title: 'Inventory',
                    html: 'Some inventory content' 
                };
                break;
            default: 
                config = {
                    xtype: 'layout.landing'
                };
                break;
        }
        me.updateCenterRegion( config );
    },
    /**
     * Updates center region of app with passed configuration
     * @param {Object} config
     * @private
     */
    updateCenterRegion: function( config ) {
        var me = this,
            center = me.getCenterRegion();
        
        // remove all existing content
        center.removeAll( true );
        // add new content
        center.add( config );
    }
});