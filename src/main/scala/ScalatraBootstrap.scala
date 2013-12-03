import _root_.akka.actor.{ActorSystem, Props}
import org.scalatra._
import javax.servlet.ServletContext
import com.pressassociation.guide.resource._

class ScalatraBootstrap extends LifeCycle {

  val system = ActorSystem()

  override def init(context: ServletContext) {
    context.mount(new CategoryResource(system), "/category/*")
    context.mount(new ChannelResource(system), "/channel/*")
    context.mount(new PersonResource(system), "/person/*")
    context.mount(new MovieResource(system), "/movie/*")
    context.mount(new SeriesResource(system), "/series/*")
    context.mount(new EpisodeResource(system), "/episode/*")
    context.mount(new ScheduleResource(system), "/schedule/*")
  }

  override def destroy(context:ServletContext) {
    system.shutdown()
  }
}
