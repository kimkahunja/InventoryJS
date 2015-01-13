/**
 * Abstract REST proxy 
 */
Ext.define('kahunja.proxy.Rest', {
    extend: 'Ext.data.proxy.Rest',
    alias: 'proxy.baserest',
    format: 'json',
    limitParam: 'max',
    startParam: 'offset',
    sortParam: 'sortorder',
    writer: {
        type: 'json',
        writeAllFields: true
    },
    reader: {
        type: 'json',
        root: 'data',
        totalProperty: 'count'
    }
});