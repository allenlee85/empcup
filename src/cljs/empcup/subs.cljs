(ns empcup.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :app-panel
 (fn [db]
   (:app-panel db)))

(re-frame/reg-sub
 :rolled-punishment
 (fn [db]
   (:rolled-punishment db)))

(re-frame/reg-sub
 :dice-state
 (fn [db]
   (:dice-state db)))

(re-frame/reg-sub
 :card
 (fn [db]
   (:card db)))
