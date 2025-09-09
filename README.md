Columbus Events is an Application that scrapes the internet to find events around Columbus and sends me a daily text informing me of the events going on that day, who is performing, and where/when the event takes place.

This Application is solely a pet project to help inform me of what is going on in my city, as well as an exercise to hone my Developer skills.

This Application mostly uses AWS infrastructure, as well as Twilio (to send the text message). There is an Amazon Event Bridge scheduled every day at 9am EST that runs the Lambda function, where the app is housed. The app processes each venue, makes a list of only the events with a date of today, and sends me the text message.

Lambda Handler calls the method that kicks off the whole process:
com.ColumbusEventAlertService.services.TextMessageService::sendTodaysEvents

Application is set up to run locally, using logic in the main method