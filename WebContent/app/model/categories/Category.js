/**
 * Model representing a Categories object
 */

Ext.define('kahunja.model.categories.Category',{
	extend:'kahunja.model.Base',
	idProperty: 'catCode',
	fields:[
	        {
	        	//Id field
	        	 name: 'catCode',
	             type: 'int',
	             useNull : true
	        },
	        {
	        	 name: 'catShtDesc',
	             type: 'string'
	        },
	        {
	        	 name: 'catDescription',
	             type: 'string'
	        }
	        ],
});