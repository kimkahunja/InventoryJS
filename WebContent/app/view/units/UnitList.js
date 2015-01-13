Ext.define('kahunja.view.units.UnitList',{
	extend:'Ext.grid.Panel',
	alias:'widget.units.unitlist',
	requires: [
	           'Ext.grid.plugin.RowEditing',
	           'Ext.toolbar.Paging'
	       ],
	//store: Ext.create('store.categories.categories'),	
   // width:'80%',	       
	autoWidth:true,       
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
                       dataIndex: 'untShtDesc',
                       editor: {
                           xtype: 'textfield',
                           allowBlank: false
                       },
                       flex: .2
                   },
                   {
                       text: 'Name',
                       dataIndex: 'untDesc',
                       editor: {
                           xtype: 'textfield',
                           allowBlank: false
                       },
                       flex: .3
                   },
                   {
                       text: 'Standard Precision',
                       dataIndex: 'untStdPrecision',
                       editor: {
                           xtype: 'textfield',
                           allowBlank: false
                       },
                       flex: .1
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
                           text: 'Add Unit'
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