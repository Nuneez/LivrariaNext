window.onload = function() {
  window.liveSearch({
    cliente: function(element, callback) {
      var url = '/LivrariaNext/ListarClientes?';
      if (element.value.length > 3) {
        window.ajaxGet(url, function(response) {
          console.log(response);
          // callback(, element);
        });
      }
    },
    produto: function(element, callback) {
      console.log("Produto");
      if (element.value.length > 3) {
        // window.ajaxGet();
        callback([{
            name: 'aaaaaa',
            id: 1
          },
          {
            name: 'bbbbbb',
            id: 2
          },
          {
            name: 'cccccccc',
            id: 3
          }
        ], element);
      }
    }
  });
};
