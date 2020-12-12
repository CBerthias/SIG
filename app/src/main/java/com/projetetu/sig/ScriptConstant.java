package com.projetetu.sig;

class ScriptConstant {

    public static final String HERE_SCRIPT  = "" +
            "const iconFeature = new ol.Feature({\n" +
            "        geometry: new ol.geom.Point([%XH%,%YH%]),\n" +
            "        name: 'You are here',\n" +
            "    });\n" +
            "\n" +
            "    var pointLayer = new ol.layer.Vector({\n" +
            "        source: new ol.source.Vector({\n" +
            "            features: [iconFeature]\n" +
            "        }),\n" +
            "        style: new ol.style.Style({\n" +
            "            image: new ol.style.Icon({\n" +
            "                anchor: [0.5, 1],\n" +
            "                anchorXUnits: 'fraction',\n" +
            "                anchorYUnits: 'fraction',\n" +
            "                src: 'https://icon-library.com/images/you-are-here-icon/you-are-here-icon-2.jpg',\n" +
            "                scale: [0.1,0.1]\n" +
            "             })\n" +
            "        })\n" +
            "    });\n" +
            "\n" +
            "    map.addLayer(pointLayer);";
}
