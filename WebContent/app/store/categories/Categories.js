/*
 * Store for managing item categories
 */
Ext.define('kahunja.store.categories.Categories',{
	extend:'Ext.data.Store',
	alias: 'store.categories.categories',
	requires:[
	          'kahunja.model.categories.Category'
	          ],
	model:'kahunja.model.categories.Category',  
	//restPath: 'resources/categories',
	storeId: 'Categories',
	//autoLoad: true,
    pageSize: 20,
    proxy: {
        type: 'ajax',
        url: 'CategoryServlet',//'resources/categories.json',
        extraParams: {
            store_id: 1
        },
        reader: {
            type: 'json',
            totalProperty: 'count',
            root: 'data',
            successProperty: 'success'
        },
     }
});
