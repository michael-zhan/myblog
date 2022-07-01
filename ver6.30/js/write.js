var testEditor;

$(function() {
    contextEditor = editormd("editormd", {
        width   : "100%",
        height  : 640,
        syncScrolling : "single",
        path    : "../lib/editormd/lib/"
    });

    /*
    // or
    testEditor = editormd({
        id      : "editormd",
        width   : "100%",
        height  : 640,
        path    : "lib/editormd/lib/"
    });
    */
});