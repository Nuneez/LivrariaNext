window.onload = function() {
  window.liveSearch({
    cliente: function(element, callback) {
      console.log("Cliente");
      if (element.value.length >= 3) {
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
    },
    produto: function(element, callback) {
        if (element.value.length >= 3) {        
        var url = document.forms[0].action.replace('ManterPedidos', 'Produtos') + "?search=" + document.querySelector("#produto").value;
        fetch(url)
                .then(response => response.json())
                .then(json => callback(json, element));
      }
    }
  });
};
