(ns empcup.events
  (:require [re-frame.core :as re-frame]
            [empcup.db :as db]
            [empcup.game :as game]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 :select-panel
 (fn  [db [_ panel]]
   (assoc db :app-panel panel)))

(re-frame/reg-event-db
 :roll-punishment
 (fn [db ev]
   (js/setTimeout #(re-frame/dispatch [:dice-lands])
                  600)
   (assoc db :dice-state :rolling)))

(re-frame/reg-event-db
 :dice-lands
 (fn [db ev]
   (-> db
       (assoc :dice-state :rolled)
       (assoc :rolled-punishment (rand-nth game/punishments)))))

(re-frame/reg-event-db
 :select-card
 (fn [db [_ card]]
   (print db)
   (assoc db :card card)))
