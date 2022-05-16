  const googleMap = new Vue({
    el: "#app",
    data: {
      map: null,
      features: [], // 存入每一個地點
      infowindowAll: {}, // 存入每一個marker上的info windows
    },
    methods: {
      // init google map
      initMap() {
        // 預設顯示的地點：聖德基督學院
        let location = {
          lat: 24.9776975, // 經度
          lng: 121.2200523, // 緯度
        };

        // 建立地圖
        this.map = new google.maps.Map(document.getElementById("map"), {
          center: location,
          zoom: 14.02,
          mapTypeId: "terrain",
        });

        // 放置多個marker
        fetch("./map.geojson")
          .then((results) => results.json())
          .then((result) => {
            this.features = result.features;
            Array.prototype.forEach.call(this.features, (r) => {
              let latLng = new google.maps.LatLng(
                r.geometry.coordinates[0],
                r.geometry.coordinates[1]
              );
              let marker = new google.maps.Marker({
                position: latLng,
                map: this.map,
              });

              // info window
              let infowindow = new google.maps.InfoWindow({
                content: `<h6>${r.properties.name}</h6>`, // 支援html
              });

              // 監聽 marker click 事件
              marker.addListener("click", (e) => {
                infowindow.open(this.map, marker);
              });

              // 加一個open的method，就可由外部按鈕開啟
              this.infowindowAll[r.properties.id] = {
                open: function () {
                  infowindow.open(this.map, marker);
                },
              };
            });
          });
      },
      // 由外部按鈕開啟info windows
      openInfoWindows(id) {
        this.infowindowAll[id].open();
      },
    },
    created() {
      window.addEventListener("load", () => {
        this.initMap();
      });
    },
  });