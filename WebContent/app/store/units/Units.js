/*
 * Store for managing item Units
 */
Ext.define('kahunja.store.units.Units',{
	extend:'Ext.data.Store',
	alias: 'store.units.units',
	requires:[
	          'kahunja.model.units.Unit'
	          ],
	model:'kahunja.model.units.Unit',  
	//restPath: 'resources/categories',
	storeId: 'Units',
	//autoLoad: true,
    pageSize: 20,
    proxy: {
        type: 'ajax',
        url: 'resources/unit.json',//'UnitServlet',
        extraParams: {
            store_id: 4
        },
        reader: {
            type: 'json',
            totalProperty: 'count',
            root: 'data',
            successProperty: 'success'
        },
     }
});
