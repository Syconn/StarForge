package mod.stf.syconn.common.entity.goal;

import mod.stf.syconn.common.entity.StormTrooper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.PatrollingMonster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class PatrolGoal<T extends StormTrooper> extends Goal {
    private final T mob;
    private final double speedModifier;
    private final double leaderSpeedModifier;
    private long cooldownUntil;

    public PatrolGoal(T p_33084_, double speed, double leaderSpeedModifier) {
        this.mob = p_33084_;
        this.speedModifier = speed;
        this.leaderSpeedModifier = leaderSpeedModifier;
        this.cooldownUntil = -1L;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        this.setFlags(EnumSet.of(Goal.Flag.JUMP));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        boolean flag = this.mob.level.getGameTime() < this.cooldownUntil;
        return this.mob.getTarget() == null && !this.mob.isVehicle() && !flag;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        boolean flag = this.mob.isLeader();
        PathNavigation pathnavigation = this.mob.getNavigation();
        if (pathnavigation.isDone() && mob.getPatrolTarget() != null){
            Vec3 vec3 = Vec3.atBottomCenterOf(this.mob.getPatrolTarget());
            Vec3 vec31 = this.mob.position();
            Vec3 vec32 = vec31.subtract(vec3);
            vec3 = vec32.yRot(90.0F).scale(0.4D).add(vec3);
            Vec3 vec33 = vec3.subtract(vec31).normalize().scale(10.0D).add(vec31);
            BlockPos blockpos = new BlockPos(vec33);
            blockpos = this.mob.level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, blockpos);

            if (flag){
                List<StormTrooper> list = this.findPatrolCompanions();
                if (this.mob.getPatrolTarget().closerThan(this.mob.position(), 10.0D)) {
                    this.mob.findPatrolTarget();
                    System.out.println(blockpos);
                }
                else if (mob.getPatrolTarget() != null){
                    if (!pathnavigation.moveTo(blockpos.getX(), blockpos.getY(), blockpos.getZ(), 1D)) {
                        this.moveRandomly();
                        this.cooldownUntil = this.mob.level.getGameTime() + 80L;
                    }

                    BlockPos finalBlockpos = blockpos;
                    list.forEach(mob2 -> {
                        mob2.setPatrollingTarget(new BlockPos(finalBlockpos));
                    });
                }
            }

            else {
                if (this.mob.getPatrolTarget().closerThan(this.mob.position(), 10.0D) || !pathnavigation.moveTo(this.mob.getPatrolTarget().getX(), this.mob.getPatrolTarget().getY(), this.mob.getPatrolTarget().getZ(), 1.6D)) {
                    this.moveRandomly();
                    this.cooldownUntil = this.mob.level.getGameTime() + 80L;
                }
            }
        }
    }

    private List<StormTrooper> findPatrolCompanions() {
        return this.mob.level.getEntitiesOfClass(StormTrooper.class, this.mob.getBoundingBox().inflate(20.0D), (p_33089_) -> {
            return  !p_33089_.is(this.mob);
        });
    }

    private boolean moveRandomly() {
        Random random = this.mob.getRandom();
        BlockPos blockpos = this.mob.level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, this.mob.blockPosition().offset(-8 + random.nextInt(16), 0, -8 + random.nextInt(16)));
        return this.mob.getNavigation().moveTo((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), this.speedModifier);
    }
}
