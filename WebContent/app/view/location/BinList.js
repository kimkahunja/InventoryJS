Ext.define('kahunja.view.location.BinList',{
	extend:'Ext.grid.Panel',
	alias:'widget.location.binlist',
	requires: [
	           'Ext.grid.plugin.RowEditing',
	           'Ext.toolbar.Paging'
	       ],
	//store: Ext.create('kahunja.store.location.Bins'),	 
	title:'Location Bins',       
    initComponent: function() {
       var me = this;
       Ext.applyIf(me,{
           selType: 'rowmodel', 
           plugins: [
               {
                   ptype: 'rowediting',
                   clicksToEdit: 2
               }
           ],
           columns: {
               defaults: {},
               items: [
                   {
                       text: 'Abbreviation',
                       dataIndex: 'lbnShtDesc',
                       editor: {
                           xtype: 'textfield',
                           allowBlank: false
                       },
                       flex: .2
                   },
                   {
                       text: 'Name',
                       dataIndex: 'lbnDescription',
                       editor: {
                           xtype: 'textfield',
                           allowBlank: false
                       },
                       flex: .5
                   }
               ]
           },
           dockedItems: [
                         {
                             xtype: 'toolbar',
                             dock: 'top',
                             ui: 'footer',
                             items: [
                                 {
                                     xtype: 'button',
                                     itemId: 'add',
                                     iconCls: 'icon_add',
                                     text: 'Add Bin'
                                 }
                             ]
                         },
                         {
                             xtype: 'pagingtoolbar',
                             ui: 'footer',
                             defaultButtonUI: 'default',
                             dock: 'bottom',
                             displayInfo: true,
                             store: me.getStore()
                         }
                     ]
       });
       me.callParent( arguments );
   }        
});