(ns empcup.views
  (:require [re-frame.core :as re-frame]
            [reagent.core :as reagent]
            [empcup.game :as game]
            [cljsjs.react-bootstrap]))

(def Navbar (reagent/adapt-react-class (aget js/ReactBootstrap "Navbar")))
(def NavbarHeader (reagent/adapt-react-class (-> js/ReactBootstrap (aget "Navbar") (aget "Header"))))
(def NavbarToggle (reagent/adapt-react-class (-> js/ReactBootstrap (aget "Navbar") (aget "Toggle"))))
(def NavbarCollapse (reagent/adapt-react-class (-> js/ReactBootstrap (aget "Navbar") (aget "Collapse"))))

(def Nav (reagent/adapt-react-class (aget js/ReactBootstrap "Nav")))
(def NavItem (reagent/adapt-react-class (aget js/ReactBootstrap "NavItem")))
(def NavbarBrand (reagent/adapt-react-class (aget js/ReactBootstrap "NavbarBrand")))
(def ButtonToolbar (reagent/adapt-react-class (aget js/ReactBootstrap "ButtonToolbar")))
(def ButtonGroup (reagent/adapt-react-class (aget js/ReactBootstrap "ButtonGroup")))
(def Button (reagent/adapt-react-class (aget js/ReactBootstrap "Button")))

(def Tabs (reagent/adapt-react-class (aget js/ReactBootstrap "Tabs")))
(def Tab (reagent/adapt-react-class (aget js/ReactBootstrap "Tab")))
(def Accordion (reagent/adapt-react-class (aget js/ReactBootstrap "Accordion")))
(def Panel (reagent/adapt-react-class (aget js/ReactBootstrap "Panel")))

(defn about-panel []
  [:div.app-panel
   [:h1 "Emperor's Cup"]
   [:h3 "Setting Up"]
   [:p "Take a deck of cards with all Jokers removed."]
   [:p "Lay the cards face down on the table."]
   [:h3 "Playing"]
   [:p "Players alternate drawing cards."]
   [:p "Once a card is drawn, the " [:a {:onClick #(re-frame/dispatch [:select-panel :cards])} "RULES"] " for the card must be obeyed. Failure to adhere to the rules results in " [:a {:onClick #(re-frame/dispatch [:select-panel :punishsments])} "PUNISHMENT"] "."]
   [:h3 "Game Over"]
   [:p "The game continues until all four Kings have been drawn."]]
  )

(defn cards-panel []
  (let [current-card (re-frame/subscribe [:card])]
    (fn []
      [:div.app-panel
       [:h1 "Cards"]
       [:div.scrollmenu
        (for [{:keys [card rule]} game/cards]
          [:a {:href "#" :onClick #(re-frame/dispatch [:select-card card])} card])]
       
       (if @current-card
         [:div.card-panel
          [:h3 (:name (game/get-card @current-card))]
          (:rule (game/get-card @current-card))])])))

(defn punishments-panel []
  (let [dice-state (re-frame/subscribe [:dice-state])
        rolled (re-frame/subscribe [:rolled-punishment])]
    (fn []
     [:div.app-panel
      [:h1 "Punishments"]
      [:div
       [:ol
         (for [pun game/punishments]
           [:li (if (and (not= @dice-state :rolling) (= @rolled pun))
                  [:span {:style {:color "#e65c00" :fontWeight "bold" :fontSize "120%"}} pun]
                  pun)])]]
      [:div {:style {:textAlign "center"}}
       [Button {:bsStyle "danger"
                :bsSize "lg"
                :className "punish-btn"
                :disabled (= @dice-state :rolling)
                :onClick #(re-frame/dispatch [:roll-punishment])} "PUNISH ME"]]]
      )))

(defn app-navbar []
  [Navbar {:inverse true :collapseOnSelect true :staticTop true :fluid true}
    [NavbarHeader
     [NavbarBrand {:onClick #(re-frame/dispatch [:select-panel :about])} "Emperor's Cup"]
     [NavbarToggle]]
    [NavbarCollapse
     [Nav {:onSelect (fn [e] (re-frame/dispatch [:select-panel (keyword e)]))}
      [NavItem {:eventKey :cards :href "#"} "Cards"]
      [NavItem {:eventKey :punishsments :href "#"} "Punishments"]]]])

(defn app-panel []
  (let [panel (re-frame/subscribe [:app-panel])]
    (fn []
      (case @panel
        :about [about-panel]
        :cards [cards-panel]
        :punishsments [punishments-panel]))))

(defn main-panel []
  [:div
   [app-navbar]
   [app-panel]
   ])
