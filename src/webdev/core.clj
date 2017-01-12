(ns webdev.core

(:require [ring.adapter.jetty :as jetty]
		  [ring.middleware.reload :refer[wrap-reload]]
		  [compojure.core :refer [defroutes GET]]
          [compojure.route :refer [not-found]]))

(defn greet [req]
			{:status 200
	 		:body "Hello, World2!"
	 		:headers {}}
	 	)
	 	
(defn goodbye [req]
	{:status 200
	 :body "Goodbye, cruel world!"
	 :headers{}})
	 
(defn about [req]
	{:status 200
	 :body "My name is Nicole Spicer and this is MTV Cribs."
	 :headers {}})
	 
(defn request [req]
	{:status 200
	 :body (pr-str req)
	 :headers {}})
	 
	
(defroutes app
	(GET "/" [] greet)
	(GET "/goodbye" [] goodbye)
	(GET "/about" [] about)
	(GET "/request" [] request)
	(not-found "Page not found."))

(defn -main [port]	
	(jetty/run-jetty app                 {:port (Integer. port)}))
	
(defn -dev-main [port]
	(jetty/run-jetty (wrap-reload #'app) {:port (Integer. port)}))