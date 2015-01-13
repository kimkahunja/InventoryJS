/*
 * Store for managing item Location Bins
 */
Ext.define('kahunja.store.location.Bins',{
	extend:'Ext.data.Store',
	alias: 'store.location.bins',
	requires:[
	          'kahunja.model.location.Bin'
	          ],
	model:'kahunja.model.location.Bin',  
	//restPath: 'resources/categories',
	storeId: 'Bins',
	//autoLoad: true,
    pageSize: 20,
    proxy: {
        type: 'ajax',
        url: 'CategoryServlet',//'resources/categories.json',
        extraParams: {
            store_id: 3
        },
        reader: {
            type: 'json',
            totalProperty: 'count',
            root: 'data',
            successProperty: 'success'
        },
     }
});
