(ns empcup.views
  (:require [re-frame.core :as re-frame]
            [reagent.core :as reagent]
            [cljsjs.react-bootstrap]))

(def Button (reagent/adapt-react-class (aget js/ReactBootstrap "Button")))
(def Tabs (reagent/adapt-react-class (aget js/ReactBootstrap "Tabs")))
(def Tab (reagent/adapt-react-class (aget js/ReactBootstrap "Tab")))
(def Accordion (reagent/adapt-react-class (aget js/ReactBootstrap "Accordion")))
(def Panel (reagent/adapt-react-class (aget js/ReactBootstrap "Panel")))

(defn about-panel []
  [:div
   [:h1 "About"]
   [:p "Emperor's Cup"]])

(defn cards-panel []
  [:div
   [:h1 "Cards"]
   [Accordion
    [Panel {:header "King" :eventKey 1}
     "King CARD"]
    [Panel {:header "Queen" :eventKey 2}
     "Queen Card"]]
   ])

(defn punishments-panel []
  [:div
   [:h1 "Punishments"]
   [:p "Emperor's Cup"]])

(defn main-panel []
  [:div
   [Tabs {:defaultActiveKey 1}
    [Tab {:id "tab-about" :eventKey 1 :title "About"}
     [about-panel]]
    [Tab {:id "tab-cards" :eventKey 2 :title "Cards"}
     [cards-panel]]
    [Tab {:id "tab-punishments" :eventKey 3 :title "Punishments"} "Tab 2 contents"]]
   ])
