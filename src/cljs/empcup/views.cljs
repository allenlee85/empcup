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
  [:div
   [:h1 "About"]
   [:p "Emperor's Cup"]])

(defn cards-panel []
  [:div
   [:h1 "Cards"]
   ;; There seems to be a bug passing a react-element as :header with
   ;; a for loop directly nested in hiccup, so we'll build up the
   ;; accordion manually instead.
   (apply conj [Accordion]
          (for [{:keys [card rule]} game/cards]
            [Panel {:header (reagent/as-element [:div {:style {:width "100%"}} card])
                    :eventKey card}
             rule]))])

(defn punishments-panel []
  (let [dice-state (re-frame/subscribe [:dice-state])
        rolled (re-frame/subscribe [:rolled-punishment])]
    (fn []
     [:div
      [:h1 "Punishments"]
      [:div
       [:ol
         (for [pun game/punishments]
           [:li pun])]]
      [:div
       [Button {:bsStyle "danger"
                :onClick #(if (not= @dice-state :rolling) (re-frame/dispatch [:roll-punishment]))} "PUNISH ME"]
       (case @dice-state
         :rolled [:div [:strong @rolled]]
         :rolling [:div (str "Rolling...")]
         [:div])]]
      )))

(defn app-navbar []
  [Navbar {:inverse true :collapseOnSelect true :staticTop true :fluid true}
    [NavbarHeader
     [NavbarBrand "Emperor's Cup"]
     [NavbarToggle]]
    [NavbarCollapse
     [Nav {:onSelect (fn [e] (re-frame/dispatch [:select-panel (keyword e)]))}
      [NavItem {:eventKey :cards :href "#"} "Cards"]
      [NavItem {:eventKey :punishsments :href "#"} "Punishments"]]]])

(defn app-panel []
  (let [panel (re-frame/subscribe [:app-panel])]
    (fn []
      (case @panel
        :cards [cards-panel]
        :punishsments [punishments-panel]))))

(defn main-panel []
  [:div
   [app-navbar]
   [app-panel]
   ])
